STSEG SEGMENT PARA STACK 'STACK'
	DB 64 DUP ( 'STACK' )
STSEG ENDS

DSEG SEGMENT PARA PUBLIC 'DATA' 
	s_greetings db 'Greetings!$'
	s_enter_array db 'Enter array of numbers (max array length = 8) $'
	s_input_finished db 'Entered array: $'
	s_sorted_array db 'Sorted array: $'
	s_max db 'Max number: $'
	s_min db 'Min number: $'
	s_separator db ', $'
	s_goodbye db 'Goodbye!$'

	s_enter_num  db 'Enter number -32768 ... 32767 (press enter to stop entering process): $'

	s_error db 'ERROR! $'
	s_empty_input_err db 'Empty input $'
	s_incorrect_symbol_err db 'Incorrect symbol $'
	s_overflow_err db 'Overflow $'
		
	endline  db 13,10,'$'
	is_negative db 0
	buffer db 9 DUP('?')	

	max_array_size 	db 8
	shift dw 0
	array_size db 0
	array dw 10 DUP('?')
DSEG ENDS

CSEG SEGMENT PARA PUBLIC 'CODE'

	MAIN PROC FAR
		ASSUME CS: CSEG, DS: DSEG, SS: STSEG

		MOV AX, DSEG
		MOV DS, AX
		
		lea di, s_greetings
		call print_str
		call print_endline
		
		lea di, s_enter_array
		call print_str
		call print_endline
		
		push cx
		push si
		xor si, si ; shift
		array_input_loop:
			mov ch, array_size
			mov cl, max_array_size
			cmp cl, ch     
    			je exit_array_input_loop
			clc
			
			lea di,s_enter_num
    			call print_str	
		
			input_loop:
				xor ax, ax
				call input_str
				test al, al
				jz exit_array_input_loop
				call str_to_word 
				jnc step
				call print_endline
				jmp input_loop

				step:
					call print_num
					call print_endline
					mov si, shift
					mov array[si], ax
					inc array_size
					add shift, 2
					jmp array_input_loop
							
		exit_array_input_loop:
			call print_endline
			lea di, s_input_finished
			call print_str
			call print_array
			call print_endline
			
			lea di, s_sorted_array
			call print_str
			call bubble_sort
			call print_array
			call print_endline
			
			lea di, s_max
			call print_str
			call max_number
			
			lea di, s_min
			call print_str
			call min_number
			
			call exit_program
			pop si
			pop cx
			ret

	main endp


	bubble_sort proc 
		push cx
		push si
		push bx
		push dx
		push ax
		
		xor cx, cx
		xor si, si
		xor bx, bx
		xor ax, ax
		xor dx, dx
		
		mov cl, array_size    
		dec cx               

		outer_loop:               
			mov bx, cx
			mov si, 0 

			inner_loop:
				mov ax, array[si]
				mov dx, array[si+2]
				cmp ax, dx
				jl noswap 
				mov array[si],dx
				mov array[si+2],ax
				
			noswap: 
				add si, 2
				dec bx
				jnz inner_loop
				loop outer_loop       
		pop ax
		pop dx
		pop bx
		pop si
		pop cx
		ret
	bubble_sort endp
	
	min_number proc 
		;array - sorted
		mov ax, array[0]
		call print_num
		call print_endline
		ret
	min_number endp
	
	max_number proc
		;array - sorted
		;shift - shift to last item
		push si
		xor si, si
		mov si, shift
		mov ax, array[si-2]
		call print_num
		call print_endline
		pop si
		ret
	max_number endp
	
	print_array proc
		push cx
		push si
		xor cx, cx
		xor si, si
		
		mov cl, array_size

		print_array_loop:
			xor ax, ax
			mov ax, array[si]
			call print_num
			lea di, s_separator
			call print_str
			add si, 2
			loop print_array_loop
		
		pop si
		pop cx
		ret
	print_array endp

	input_str proc	    
    		mov ah,0Ah	    
    		mov [buffer], 7; set max input length into first byte
    		mov byte[buffer+1],0 
    		lea dx,buffer ;DX = input adress  
    		int 21h		    
    		mov al,[buffer+1] ;AL = input length	    
    		add dx,2 ;DX = str address			    
    		ret
	input_str endp

	str_to_word proc
		push cx		    
    		push si
    		push di
    		push bx		    
    		push dx
		
		mov bx,dx 		    
    		mov bl,[bx] ; first byte of str     
    		cmp bl,'-' ; bl equal '-' ?		    
    		je negative_number	   		    
    		jmp positive_number

		positive_number:
			mov is_negative, 0
			jmp unsigned_str_to_word

		negative_number:
			inc dx		   
    			dec al	
			mov is_negative, 1
    			jmp unsigned_str_to_word
		
		unsigned_str_to_word:
			mov si, dx ; str address
    			mov di, 10 ; number system base
    			xor cx, cx ; counter
			mov cl,al ; str length 		      
    			xor ax, ax ; result		   
    			xor bx, bx
			
			unsigned_str_to_word_loop:
    				
				mov bl,[si] ; current symbol   
    				inc si 
	
				;check if code symbol is in range from '0' to '9'	   
    				cmp bl,'0' 		    
    				jl incorrect_symbol_error	    
    				cmp bl,'9'		    
    				jg incorrect_symbol_error
				sub bl,'0' ;get number from symbol
	    		
    				mul di ; ax = ax * 10	
				jc overflow_error      
    				add ax, bx
				jc overflow_error		    
  
				loop unsigned_str_to_word_loop
				
			cmp is_negative, 1
			je make_negative
			clc ; clear carry flag
			jmp str_to_word_exit
	
			make_negative:	
				neg ax 
				clc
				jmp str_to_word_exit	
			
		incorrect_symbol_error:
			lea di, s_incorrect_symbol_err	    
			call error_handler
			jmp str_to_word_exit
	
		overflow_error:
			lea di, s_overflow_err	
			call error_handler 
			jmp str_to_word_exit	
		   
		str_to_word_exit: 			
			pop cx		    
    			pop si
    			pop di
    			pop bx		    
    			pop dx
    			ret
			
	str_to_word endp

	print_str proc far
    		push ax
    		mov ah,9		    
    		xchg dx,di	    
    		int 21h		    
    		xchg dx,di	
    		pop ax	    
    		ret
	print_str endp

	print_endline proc
    		lea di,endline	    
    		call print_str	    
    		ret
	print_endline endp

	error_handler proc
		call print_str
		call print_endline
		call print_endline
		stc ; set cf = 1
		ret
	error_handler endp
	
	exit_program proc
		mov ah,4ch
    		int 21h
		ret
	exit_program endp
		
	print_num proc
    		push ax
    		push bx
    		push dx
			push cx
		
    		mov bx, ax
    		or bx, bx ; sf = 1 if bx is negative
    		jns positive ; sf == 0

    		mov ah,2 
	; sends a character from DL to standard output, 
	;moving the cursor to the left one position and leaving it in a new position
    		mov dl,'-' 
    		int 21h

    		neg bx

		positive:
   		  	mov ax, bx
    		xor cx, cx ; number of characters to print
    		mov bx, 10

		print_num_loop:
    		  xor dx, dx ; reminder of the devision
    		  div bx ; ax / bx, ax - result of the devision
    		  add dl,'0' ; add '0' to get symbol code
    		  push dx 
    		  inc cx 
    		  test ax, ax 
    		  jnz print_num_loop ; zf == 0

		output_loop:
    		  	mov ah,2
    		  	pop dx
   		  	int 21h
                  	loop output_loop
    		  
		  pop cx
    	 	  pop dx
    		  pop bx
    		  pop ax
    		  ret
	print_num endp

CSEG ENDS 
END MAIN

 
	    
