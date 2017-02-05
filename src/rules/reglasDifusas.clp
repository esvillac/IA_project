;Reglas
(defrule Regla1
	(Peso_Reprobado 
		(Peso Peligro) 
                (CF ?N) 

	) 
	 => 
	(assert (RespuestaDifusa
		(Categoria Peligro_Prueba)
                (CF ?N) 
		)
	) 
) 
(defrule Regla2
	(Peso_Atenuantes
		(Peso Muy_Dificil) 
                (CF ?N) 
	) 
	 => 
	(assert (RespuestaDifusa
		(Categoria Muy_Facil)
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
		(Categoria Peligro_Reprobadas)
                (CF ?N) 
		)
	) 
) 
(defrule Regla4
	(Peso_Reprobado 
		(Peso A_Salvo) 
                (CF ?N1) 

	) 
	(Peso_Atenuantes
            (Peso No_Atenuantes|Regular) 
            (CF ?N2) 
        ) 
	(Peso_Promedio
		(Peso Bajo) 
                 (CF ?N3)
	)
	 => 
        (bind ?Total (min ?N1 ?N2 ?N3) )
	(assert (RespuestaDifusa
		(Categoria Facil)
                (CF ?Total)
		)
	) 
) 
(defrule Regla5
	(Peso_Reprobado 
		(Peso A_Salvo) 
                (CF ?N1) 

	) 
	(Peso_Atenuantes
		(Peso No_Atenuantes) 
                (CF ?N2) 
	) 
	(Peso_Promedio
		(Peso Regular) 
                (CF ?N3) 
	)
	 => 
        (bind ?Total (min ?N1 ?N2 ?N3) )
	(assert (RespuestaDifusa
		(Categoria Regular)
                (CF ?Total) 
		)
	) 
) 
(defrule Regla6
	(Peso_Reprobado 
		(Peso A_Salvo) 
                (CF ?N1) 

	) 
	(Peso_Atenuantes
		(Peso No_Atenuantes) 
                (CF ?N2) 
	) 
	(Peso_Promedio
		(Peso Bueno) 
                (CF ?N3) 
	)
	 => 
        (bind ?Total (min ?N1 ?N2 ?N3) )
	(assert (RespuestaDifusa
		(Categoria Dificil)
                (CF ?Total) 
		)
	) 
) 
(defrule Regla7
	(Peso_Reprobado 
		(Peso A_Salvo) 
                (CF ?N1) 
	) 
	(Peso_Atenuantes
		(Peso No_Atenuantes) 
                (CF ?N2) 
	) 
	(Peso_Promedio
		(Peso Sobresaliente)
                (CF ?N3)  
	)
	 => 
        (bind ?Total (min ?N1 ?N2 ?N3) )
	(assert (RespuestaDifusa
		(Categoria Muy_Dificil)
                (CF ?Total) 
		)
	) 
) 
(defrule Regla8
	(Peso_Reprobado 
		(Peso A_Salvo) 
                (CF ?N1) 

	) 
	(Peso_Atenuantes
		(Peso Regular) 
                (CF ?N2) 
	) 
	(Peso_Promedio
		(Peso Regular) 
                (CF ?N3) 
	)
	 => 
        (bind ?Total (min ?N1 ?N2 ?N3) )
	(assert (RespuestaDifusa
		(Categoria Regular)
                (CF ?Total) 
		)
	) 
) 
(defrule Regla9
	(Peso_Reprobado 
		(Peso A_Salvo) 
                (CF ?N1) 

	) 
	(Peso_Atenuantes
		(Peso Regular) 
                (CF ?N2) 
	) 
		(Peso_Promedio
			(Peso Regular|Sobresaliente) 
                        (CF ?N3) 
		)

	 => 
        (bind ?Total (min ?N1 ?N2 ?N3) )
	(assert (RespuestaDifusa
		(Categoria Normal)
                        (CF ?Total) 
		)
	) 
) 
(defrule Regla10
	(Peso_Reprobado 
		(Peso A_Salvo) 
                (CF ?N1) 

	) 
	(Peso_Atenuantes
		(Peso Dificil) 
                (CF ?N2) 
	) 
	(Peso_Promedio
		(Peso Bajo) 
                (CF ?N3) 
	)

	
	 => 
        (bind ?Total (min ?N1 ?N2 ?N3) )
	(assert (RespuestaDifusa
		(Categoria Muy_Facil)
                (CF ?Total) 
		)
	) 
)
(defrule Regla11
	(Peso_Reprobado 
		(Peso A_Salvo) 
                (CF ?N1) 

	) 
	(Peso_Atenuantes
		(Peso Dificil) 
                (CF ?N2) 
	) 
	(Peso_Promedio
		(Peso Regular) 
                (CF ?N3) 
	)
	 => 
        (bind ?Total (min ?N1 ?N2 ?N3) )
	(assert (RespuestaDifusa
		(Categoria Facil)
                (CF ?Total) 
		)
	) 
)
(defrule Regla12
	(Peso_Reprobado 
		(Peso A_Salvo)
                (CF ?N1)  

	) 
	(Peso_Atenuantes
		(Peso Dificil) 
                (CF ?N2) 
	) 
		(Peso_Promedio
			(Peso Bueno|Sobresaliente) 
                        (CF ?N3) 

	)
	 => 
        (bind ?Total (min ?N1 ?N2 ?N3) )
	(assert (RespuestaDifusa
		(Categoria Regular)
                (CF ?Total) 
		)
	) 
)
(defrule Regla13
	(Peso_Reprobado 
		(Peso Alerta) 
                (CF ?N1) 

	) 
	(Peso_Atenuantes
		(Peso No_Atenuantes|Regular) 
                (CF ?N2) 
	) 

	(Peso_Promedio
		(Peso Bajo) 
                (CF ?N3) 
	)
	
	 => 
        (bind ?Total (min ?N1 ?N2 ?N3) )
	(assert (RespuestaDifusa
		(Categoria Facil)
                (CF ?Total) 
		)
	) 
)
(defrule Regla14
	(Peso_Reprobado 
		(Peso Alerta) 
                (CF ?N1) 

	) 
	(Peso_Atenuantes
		(Peso No_Atenuantes|Regular) 
                (CF ?N2) 
	) 
	(Peso_Promedio
		(Peso Regular) 
                (CF ?N3) 
	)
	
	 => 
        (bind ?Total (min ?N1 ?N2 ?N3) )
	(assert (RespuestaDifusa
		(Categoria Regular)
                (CF ?Total) 
		)
	) 
)
(defrule Regla15
	(Peso_Reprobado 
		(Peso Alerta) 
                (CF ?N1) 

	) 
		(Peso_Atenuantes
			(Peso No_Atenuantes|Regular) 
                        (CF ?N2) 
		) 
            (Peso_Promedio
                    (Peso Bueno|Sobresaliente) 
                    (CF ?N3) 
            )

	 => 
        (bind ?Total (min ?N1 ?N2 ?N3) )
	(assert (RespuestaDifusa
		(Categoria Normal)
                 (CF ?Total) 
		)
	) 
)
(defrule Regla16
	(Peso_Reprobado 
		(Peso Alerta) 
                (CF ?N1) 

	) 
	(Peso_Atenuantes
		(Peso Dificil) 
                (CF ?N2) 
	)
	 => 
(bind ?Total (min ?N1 ?N2)  )
	(assert (RespuestaDifusa
		(Categoria Facil)
                (CF ?Total)
		)
	) 
)
