;Reglas
(defrule Respuesta1
	(RespuestaDifusa 
		(Categoria Muy Facil) 

	) 
	 => 
(assert (Respuesta_Numero_Materias
		(Formativas 2)
		(Optativas 0)
		(Libre_Opcion 2)
		)
	) 
)
(defrule Respuesta2
	(RespuestaDifusa 
		(Categoria Facil) 

	) 
	 => 
(assert (Respuesta_Numero_Materias
		(Formativas 3)
		(Optativas 0)
		(Libre_Opcion 1)
		)
	) 
)
(defrule Respuesta3
	(RespuestaDifusa 
		(Categoria Regular) 

	) 
	 => 
(assert (Respuesta_Numero_Materias
		(Formativas 3)
		(Optativas 0)
		(Libre_Opcion 1)
		)
	) 
)
(defrule Respuesta4
	(RespuestaDifusa 
		(Categoria Normal) 

	) 
	 => 
(assert (Respuesta_Numero_Materias
		(Formativas 4)
		(Optativas 0)
		(Libre_Opcion 1)
		)
	) 
)
(defrule Respuesta5
	(RespuestaDifusa 
		(Categoria Dificil) 

	) 
	 => 
(assert (Respuesta_Numero_Materias
		(Formativas 5)
		(Optativas 0)
		(Libre_Opcion 1)
		)
	) 
)
(defrule Respuesta6
	(RespuestaDifusa 
		(Categoria Muy Dificil) 

	) 
	 => 
(assert (Respuesta_Numero_Materias
		(Formativas 6)
		(Optativas 0)
		(Libre_Opcion 0)
		)
	) 
)
(defrule Respuesta7
	(RespuestaDifusa 
		(Categoria Peligro Prueba) 
	) 
	(Numero_Repro    
		 (Numero ?N)
	)
	 => 
(assert (Respuesta_Numero_Materias
		(Formativas ?N)
		(Optativas 0)
		(Libre_Opcion 0)
		)
	) 
)
(defrule Respuesta8
	(RespuestaDifusa 
		(Categoria Peligro Reprobadas) 

	) 
	(Numero_Repro    
		 (Numero ?N)
	)
	 => 

(assert (Respuesta_Numero_Materias
		(Formativas ?N)
		(Optativas 0)
		(Libre_Opcion 2)
		)
	) 
)