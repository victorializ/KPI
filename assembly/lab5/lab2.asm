include macro2.txt		

STSEG SEGMENT PARA STACK 'STACK'
	DB 64 DUP ( 'STACK' )
STSEG ENDS

DSEG SEGMENT PARA PUBLIC 'DATA' 
	s_greetings db 'Greetings!$'
	s_goodbye db 'Goodbye!$'

	s_enter_num  db 'Enter number -32669...32767 (press enter to exit): $' ; -32699 - 99 = -32768
	s_result  db 'Result: $'

	s_error db 'ERROR! $'
	s_empty_input_err db 'Empty input $'
	s_incorrect_symbol_err db 'Incorrect symbol $'
	s_overflow_err db 'Overflow $'

	endline  db 13,10,'$'
	is_negative db 0
	buffer	 db 9 DUP('?')
DSEG ENDS

CSEG SEGMENT PARA PUBLIC 'CODE'
				
	MAIN PROC FAR
		ASSUME CS: CSEG, DS: DSEG, SS: STSEG

		MOV AX, DSEG
		MOV DS, AX
		
		print_string s_greetings
		print_string endline

		program_loop: 
    			print_string s_enter_num
				print_string endline
	    
    			input_string 7  
				test al,al ; zf = 0 if al != 0 	    
    			jz exit_main_proc; jz == 0 
				; program loop ends if user press enter
					
			call str_to_word 
			; proc str_to word sets cf = 1 in case of error 
			jnc sub_number 
			jmp program_loop

			sub_number:
				sub ax, 99
				jo overflow
				call print_num
   				print_string endline
				print_string endline
				jmp program_loop

			overflow:
				print_string s_overflow_err
				print_string endline
				print_string endline
				jmp program_loop
    			
		exit_main_proc:
			print_string s_goodbye
			print_string endline
			call exit_program
			ret
	main endp
	
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

    		print_string s_result
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
	
	error_handler proc
		print_string s_error
		print_string endline
		print_string endline
		stc ; set cf = 1
		ret
	error_handler endp

CSEG ENDS 
END MAIN
	
				
	
