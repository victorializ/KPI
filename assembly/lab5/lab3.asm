include macro3.txt		

STSEG SEGMENT PARA STACK 'STACK'
	DB 64 DUP ( 'STACK' )
STSEG ENDS

DSEG SEGMENT PARA PUBLIC 'DATA' 
	s_greetings db 'Greetings!$'
	s_goodbye db 'Goodbye!$'

	s_enter_num  db 'Enter number -20 ... 771 (press enter to exit): $'

	s_error db 'ERROR! $'
	s_empty_input_err db 'Empty input $'
	s_incorrect_symbol_err db 'Incorrect symbol $'
	s_overflow_err db 'Overflow $'
	
	s_in_range_1_20 db '54 + (x^2)/(1+x) = $'
	s_less_than_1 db '75 * x^2 -17*x = $'
	s_greater_than_20 db '(85 * x)/(1+x) = $'
	s_div_reminder db ' reminder $'
	
	endline  db 13,10,'$'
	is_negative db 0
	x dw 0
	buffer	 db 9 DUP('?')
DSEG ENDS

CSEG SEGMENT PARA PUBLIC 'CODE'

	MAIN PROC FAR
		ASSUME CS: CSEG, DS: DSEG, SS: STSEG

		MOV AX, DSEG
		MOV DS, AX
		
		lea di, s_greetings
		call print_str
		call print_endline

		program_loop: 
    			lea di,s_enter_num
    			call print_str	
				call print_endline
	    
    			call input_str  
				test al,al ; zf = 0 if al != 0 	    
    			jz exit_main_proc; jz == 0 
				jnz program;
				; program loop ends if user press enter
					
				exit_main_proc:
					lea di, s_goodbye
					call print_str
					call print_endline
					call exit_program
					ret
				
				program:
				call str_to_word 
				; proc str_to word sets cf = 1 in case of error 
				jnc calculate
				jmp program_loop
				
				calculate:
					cmp ax, 1
					jle func_1
					cmp ax, 20
					jbe func_2
					jmp func_3
					
				func_1:
					lea di, s_less_than_1
					call print_str
					func1
					jmp program_loop
				
				func_2: 
					lea di, s_in_range_1_20
					call print_str
					call func_2_proc
					jmp program_loop
				
				func_3: 
					lea di, s_greater_than_20
					call print_str
					call func_3_proc
					jmp program_loop
			
			overflow:
				lea di, s_overflow_err
				call print_str
				call print_endline
				call print_endline
				jmp program_loop
 
	main endp
	
	func_1_proc proc
		push bx
		push dx 
		push cx
					
		xor bx, bx
		xor dx, dx
		xor cx, cx
					
		mov x, ax
		imul ax ; ax = x^2
		jc func1_overflow
		jo func1_overflow
		mov cx, 75
		imul cx ; ax = 75*x^2
		jc func1_overflow
		jo func1_overflow
		xor cx, cx
		mov cx, 17
		mov bx, ax ; 75*x^2
		xor ax, ax
		mov ax, x ; ax = x
		imul cx ; ax = 17 * x
		jc func1_overflow
		jo func1_overflow
		sub bx, ax ; bx = 75*x^2 - 17 * x
		jo func1_overflow
		mov ax, bx 
		
		call print_num
		jmp func1_exit
		
		func1_overflow:
			lea di, s_overflow_err
			call print_str
			
		func1_exit:
			call print_endline
			call print_endline
			pop cx
			pop dx
			pop bx	
			ret
	func_1_proc endp
	
	func_2_proc proc
		push dx 
					
		xor dx, dx 
		
		mov x, ax
		mul ax ; ax = x^2
		jc func2_overflow
		jo func2_overflow
		add x, 1 ; x + 1
		jc func2_overflow
		jo func2_overflow
		div x ; al = ax / x (x^2)/(1+x) dx - reminder of the division 
		add ax, 54 ; ax = 54 + (x^2)/(1+x)
		jc func2_overflow
		jo func2_overflow
		call print_num
		
		test dx, dx
		jnz func2_reminder
		jmp func2_exit
		
		func2_overflow:
			lea di, s_overflow_err
			call print_str
			jmp func2_exit
		
		func2_reminder:
			mov ax, dx
			lea di, s_div_reminder
			call print_str
			call print_num
			
		func2_exit:
			call print_endline
			call print_endline
			pop dx
			ret
	func_2_proc endp
	
	func_3_proc proc	
		push dx 
		push cx
		
		xor dx, dx 
		xor cx, cx
		
		mov x, ax
		mov cx, 85
		mul cx ; ax = 85 * x
		jc func3_overflow
		jo func3_overflow
		add x, 1 ; x + 1
		jc func3_overflow
		jo func3_overflow
		div x ; al = ax / x (85*x)/(1+x) dx - reminder of the division 
	
		call print_num
		
		test dx, dx
		jnz func3_reminder
		jmp func3_exit
		
		func3_overflow: 
			lea di, s_overflow_err
			call print_str
			jmp func3_exit
			
		func3_reminder:
			mov ax, dx
			lea di, s_div_reminder
			call print_str
			call print_num
			
		func3_exit:
			call print_endline
			call print_endline
			pop cx
			pop dx
			ret
	func_3_proc endp
	
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

