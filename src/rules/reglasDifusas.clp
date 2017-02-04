;Reglas

(defrule Regla1
	(Peso_Reprobado 
		(Peso Peligro) 
                (CF ?N) 

	) 
	 => 
	(assert (RespuestaDifusa
		(Categoria Peligro Prueba)
                (CF ?N) 
		)
	) 
) 
(defrule Regla2
	(Peso_Atenuantes
		(Peso Muy Dificil) 
                (CF ?N) 
	) 
	 => 
	(assert (RespuestaDifusa
		(Categoria Muy Facil)
                (CF ?N) 
		)
	) 
) 
(defrule Regla3
	(Peso_Reprobado 
		(Peso Complicado) 
                (CF ?N) 

	) 
	 => 
	(assert (RespuestaDifusa
		(Categoria Peligro Reprobadas)
                (CF ?N) 
		)
	) 
) 
(defrule Regla4
	(Peso_Reprobado 
		(Peso A Salvo) 

	) 
	(or
		(Peso_Atenuantes
			(Peso No Atenuantes) 
		) 
		(Peso_Atenuantes
			(Peso Regular) 
		)
	)
	(Peso_Promedio
		(Peso Bajo) 
	)
	 => 
	(assert (RespuestaDifusa
		(Categoria Facil)
		)
	) 
) 
(defrule Regla5
	(Peso_Reprobado 
		(Peso A Salvo) 

	) 
	(Peso_Atenuantes
		(Peso No Atenuantes) 
	) 
	(Peso_Promedio
		(Peso Regular) 
	)
	 => 
	(assert (RespuestaDifusa
		(Categoria Regular)
		)
	) 
) 
(defrule Regla6
	(Peso_Reprobado 
		(Peso A Salvo) 

	) 
	(Peso_Atenuantes
		(Peso No Atenuantes) 
	) 
	(Peso_Promedio
		(Peso Bueno) 
	)
	 => 
	(assert (RespuestaDifusa
		(Categoria Dificil)
		)
	) 
) 
(defrule Regla7
	(Peso_Reprobado 
		(Peso A Salvo) 

	) 
	(Peso_Atenuantes
		(Peso No Atenuantes) 
	) 
	(Peso_Promedio
		(Peso Sobresaliente) 
	)
	 => 
	(assert (RespuestaDifusa
		(Categoria Muy Dificil)
		)
	) 
) 
(defrule Regla8
	(Peso_Reprobado 
		(Peso A Salvo) 

	) 
	(Peso_Atenuantes
		(Peso Regular) 
	) 
	(Peso_Promedio
		(Peso Regular) 
	)
	 => 
	(assert (RespuestaDifusa
		(Categoria Regular)
		)
	) 
) 
(defrule Regla9
	(Peso_Reprobado 
		(Peso A Salvo) 

	) 
	(Peso_Atenuantes
		(Peso Regular) 
	) 
	(or
		(Peso_Promedio
			(Peso Regular) 
		)
			(Peso_Promedio
			(Peso Sobresaliente) 
		)
	)
	 => 
	(assert (RespuestaDifusa
		(Categoria Normal)
		)
	) 
) 
(defrule Regla10
	(Peso_Reprobado 
		(Peso A Salvo) 

	) 
	(Peso_Atenuantes
		(Peso Dificil) 
	) 
	(Peso_Promedio
		(Peso Bajo) 
	)

	)
	 => 
	(assert (RespuestaDifusa
		(Categoria Muy Facil)
		)
	) 
)
(defrule Regla11
	(Peso_Reprobado 
		(Peso A Salvo) 

	) 
	(Peso_Atenuantes
		(Peso Dificil) 
	) 
	(Peso_Promedio
		(Peso Regular) 
	)

	)
	 => 
	(assert (RespuestaDifusa
		(Categoria Facil)
		)
	) 
)
(defrule Regla12
	(Peso_Reprobado 
		(Peso A Salvo) 

	) 
	(Peso_Atenuantes
		(Peso Dificil) 
	) 
	(or
		(Peso_Promedio
			(Peso Bueno) 
		)
		(Peso_Promedio
			(Peso Sobresaliente) 
		)
	)
	
	 => 
	(assert (RespuestaDifusa
		(Categoria Regular)
		)
	) 
)
(defrule Regla13
	(Peso_Reprobado 
		(Peso Alerta) 

	) 
	(or
		(Peso_Atenuantes
			(Peso No Atenuante) 
		) 
		(Peso_Atenuantes
			(Peso Regular) 
		)
	)
	(Peso_Promedio
		(Peso Bajo) 
	)
	
	 => 
	(assert (RespuestaDifusa
		(Categoria Facil)
		)
	) 
)
(defrule Regla14
	(Peso_Reprobado 
		(Peso Alerta) 

	) 
	(or
		(Peso_Atenuantes
			(Peso No Atenuante) 
		) 
		(Peso_Atenuantes
			(Peso Regular) 
		)
	)
	(Peso_Promedio
		(Peso Regular) 
	)
	
	 => 
	(assert (RespuestaDifusa
		(Categoria Regular)
		)
	) 
)
(defrule Regla15
	(Peso_Reprobado 
		(Peso Alerta) 

	) 
	(or
		(Peso_Atenuantes
			(Peso No Atenuante) 
		) 
		(Peso_Atenuantes
			(Peso Regular) 
		)
	)
	(Peso_Promedio
		(Peso Bueno) 
	)
	(Peso_Promedio
		(Peso Sobresaliente) 
	)
	 => 
	(assert (RespuestaDifusa
		(Categoria Normal)
		)
	) 
)
(defrule Regla16
	(Peso_Reprobado 
		(Peso Alerta) 

	) 
	(Peso_Atenuantes
		(Peso Dificil) 
	)
	 => 
	(assert (RespuestaDifusa
		(Categoria Facil)
		)
	) 
)
(defrule Respuesta1
	(RespuestaDifusa 
		(Peso Muy Facil) 

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
		(Peso Facil) 

	) 
	 => 
(assert (Respuesta_Numero_Materias
		(Formativas 2)
		(Optativas 1)
		(Libre_Opcion 1)
		)
	) 
)
(defrule Respuesta3
	(RespuestaDifusa 
		(Peso Regular) 

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
		(Peso Normal) 

	) 
	 => 
(assert (Respuesta_Numero_Materias
		(Formativas 3)
		(Optativas 1)
		(Libre_Opcion 1)
		)
	) 
)
(defrule Respuesta5
	(RespuestaDifusa 
		(Peso Dificil) 

	) 
	 => 
(assert (Respuesta_Numero_Materias
		(Formativas 4)
		(Optativas 1)
		(Libre_Opcion 1)
		)
	) 
)
(defrule Respuesta6
	(RespuestaDifusa 
		(Peso Muy Dificil) 

	) 
	 => 
(assert (Respuesta_Numero_Materias
		(Formativas 5)
		(Optativas 1)
		(Libre_Opcion 0)
		)
	) 
)
(defrule Respuesta7
	(RespuestaDifusa 
		(Peso Peligro Prueba) 
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
		(Peso Peligro Reprobadas) 

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