include macro4.txt

STSEG SEGMENT PARA STACK 'STACK'
	DB 64 DUP ( 'STACK' )
STSEG ENDS

DSEG SEGMENT PARA PUBLIC 'DATA' 
	s_greetings db 'Greetings!',13,10,'$'
	s_enter_array db 'Enter array of numbers (max length = 16)',13,10,'$'
	s_enter_num  db 'Enter number -32768 ... 32767 (press enter to stop entering process): $'
	s_input_finished db 'Entered array: $'
	s_sorted_array db 'Sorted array: $'
	s_max db 'Max number: $'
	s_min db 'Min number: $'
	s_separator db ' $'
	s_try_again db 'Try again: $'
	s_enter_two_dimen_array db 'Enter two-dimensional array',13,10,'$'
	s_first_row db 'Enter first row (max length 8)',13,10,'$'
	s_second_row db 'Enter second row ',13,10,'$'
	s_enter_number_to_find db 'Enter number to find', 13, 10, '$'
	s_i db 'i: $'
	s_j db 'j: $'
	s_min_length db 'Please, enter number! Min length: $'
	s_sum db 'Sum of numbers in array: $'
	s_res db 'Result: ',13,10,'$'
	s_goodbye db 'Goodbye!',13,10,'$'
	
	s_error db 'ERROR! $'
	s_empty_input_err db 'Empty input $'
	s_incorrect_symbol_err db 'Incorrect symbol $'
	s_overflow_err db 'Overflow $'
	s_not_found db 'Not found! $'
		
	endline  db 13,10,'$'
	is_negative db 0
	buffer db 9 DUP('?')	

	max_array_size db 16 
	min_array_size db 1
	array_size db 0
	var dw 0 ; just helper variable
	num dw 0 
	array dw 16 DUP ('?')
	i dw 0
	j dw 0
	
DSEG ENDS

CSEG SEGMENT PARA PUBLIC 'CODE'

	MAIN PROC FAR
		ASSUME CS: CSEG, DS: DSEG, SS: STSEG

		MOV AX, DSEG
		MOV DS, AX
		
		lea di, s_greetings
		call print_str

		lea di, s_enter_array
		call print_str 
		
		call input_array
		lea di, s_input_finished
		call print_str
		call print_array
		call print_endline
		
		sum_numbers
		
		call bubble_sort
		lea di, s_sorted_array
		call print_str
		call print_array
		call print_endline
			
		call max_number		
		call min_number
		
		second_arr_input:
			input_first_row: 
				lea di, s_first_row
				call print_str
				mov var, 0 
				mov array, 0
				mov array_size, 0 
				mov max_array_size, 8
				call input_array
			
			input_second_row:
				lea di, s_second_row
				call print_str
				mov cl, array_size
				mov min_array_size, cl
				mov max_array_size, cl
		
				mov array_size, 0 
				call input_array
				call print_endline
		
		find_element: 
			lea di, s_enter_number_to_find
			call print_str
			lea di, s_enter_num
			call print_str
			call print_endline
			call input_str
			test al, al
			jz exit_main
			call str_to_word
			jc exit_main
			call find_index
			jmp find_element
	
		exit_main: 
			lea di, s_goodbye
			call print_str
			call print_endline
			call exit_program
			ret
	main endp
	
	find_index proc 
		xor cx, cx
		xor si, si
		
		mov num, ax ; number to find
		mov cl, min_array_size ; min_array_size = size of array /2
		mov ax, cx 
		mov cx, 2
		mul cx ; ax = cx * 2
		mov cx, ax ; cx = sizeof array
		mov var, ax 
		find_element_loop:
			mov dx, num
			cmp dx, array[si] ; compare number with item in array
			je number_found 
			jne find_element_step
			number_found:
				mov ax, si 
				xor bx, bx
				mov bx, 2
				div bl 
				mov j, ax ; j = si / 2
				cmp var, si 
				jle second_row
				first_row:
					mov i, 0
					jmp print_indexes
				second_row:
					mov i, 1
					xor bx, bx
					mov bl, min_array_size 
					sub j, bx ; j = si/2 - (size of first row)
				print_indexes:
					lea di, s_i
					call print_str
					mov ax, i
					call print_num
					call print_endline
					lea di, s_j
					call print_str
					mov ax, j
					call print_num
					call print_endline
					jmp find_element_step
			find_element_step:
				add si, 2
				loop find_element_loop
		ret
	find_index endp

	input_array proc	
		push cx
		push si
		xor si, si ; shift
		xor cx, cx
		
		array_input_loop:
			; if current size of array larger than max array size - exit input loop
			mov ch, array_size 
			mov cl, max_array_size
			cmp cl, ch     
    		je exit_array_input_loop 
			clc
			
			lea di,s_enter_num 
    		call print_str	
			call print_endline
			
			input_loop:
				xor ax, ax
				call input_str
				test al, al
				jz on_enter_tap 
				call str_to_word 
				jnc step
				jc re_enter
				re_enter:
					call print_endline
					lea di, s_try_again
					call print_str
					jmp input_loop
				not_enough_numbers:
					call print_endline
					lea di, s_min_length
					call print_str
					xor ax, ax
					mov al, min_array_size
					call print_num
					jmp re_enter
				on_enter_tap: 
					; second row for two-dimensional array should be filled
					mov ch, array_size 
					mov cl, min_array_size
					cmp ch, cl
					jnb exit_array_input_loop
					clc
					jmp not_enough_numbers
			step:
				call print_endline
				mov si, var ; var - shift to last item in array (current array size * 2)
				mov array[si], ax ; add entered value to array
				inc array_size 
				add var, 2
				jmp array_input_loop
			
			exit_array_input_loop:
				call print_endline
				pop si
				pop cx
				ret
	input_array endp
	
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
		
		je bubble_sort_exit
		clc
		mov cl, array_size    
		dec cx ; cx - iterations number (array_size - 1)

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
				
		bubble_sort_exit: 
			pop ax
			pop dx
			pop bx
			pop si
			pop cx
			ret
	bubble_sort endp
	
	min_number proc 
		; prints first number in sorted array 
		lea di, s_min
		call print_str
		mov ax, array[0] 
		call print_num
		call print_endline
		ret
	min_number endp
	
	max_number proc
		;prints last number in sorted array 
		;var - shift to last item + 2
		lea di, s_max
		call print_str
		push si
		xor si, si
		mov si, var
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
    		mov ah,0Ah	 ; 10   
    		mov [buffer], 7; set max input length into first byte
    		;mov byte[buffer+1],0 
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


