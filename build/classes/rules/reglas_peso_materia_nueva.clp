; Reglas para poner peso a las materias nuevas 

(defrule Poner_Peso_Materia_Nueva_1
	(Materia 
		(NombreMat $?NombreMat) 
		(Creditos ?Creditos)
		(Flujo Si)
		(Proyecto Si) 
		(Tipo ?Tipo)
	) 
	(not (exists 
				(Materia_Peso 
				(NombreMat $?NombreMat) 
			)
		)
	)
(test (> ?Creditos 4))
 =>
	(assert (Materia_Peso
		(NombreMat $?NombreMat)
		(Peso 5)
		(Tipo ?Tipo)
		)
	)
		(assert (Materias_Posibles
		(NombreMat $?NombreMat)
		(Peso 5)
		(Tipo ?Tipo)
		)
	)
)
(defrule Poner_Peso_Materia_Nueva_2
	(Materia
		(NombreMat $?NombreMat) 
		(Creditos ?Creditos)
		(Flujo Si)
		(Proyecto Si) 
		(Tipo ?Tipo)
	) 
	(not (exists 
				(Materia_Peso 
				(NombreMat $?NombreMat) 
			)
		)
	)
	(test (<= ?Creditos 4))
 =>
	(assert (Materia_Peso
		(NombreMat $?NombreMat)
		(Peso 4.5)
		(Tipo ?Tipo)
		)
	)
		(assert (Materias_Posibles
		(NombreMat $?NombreMat)
		(Peso 4.5)
		(Tipo ?Tipo)
		)
	)
)
(defrule Poner_Peso_Materia_Nueva_3
	(or
		(and
			(Materia
				(NombreMat $?NombreMat) 
				(Creditos ?Creditos)
				(Flujo No)
				(Proyecto Si) 
				(Tipo ?Tipo)
			)
			(not (exists 
						(Materia_Peso 
						(NombreMat $?NombreMat) 
					)
				)
			)
			(test (> ?Creditos 4))
		) 
		(and
			(Materia 
			(NombreMat $?NombreMat) 
			(Creditos ?Creditos)
			(Flujo Si)
			(Proyecto Si) 
			(Tipo ?Tipo)
			)
			(not (exists 
						(Materia_Peso 
						(NombreMat $?NombreMat) 
					)
				)
			)
			(test (<= ?Creditos 4))
		) 

	)
 =>
	(assert (Materia_Peso
		(NombreMat $?NombreMat)
		(Peso 4.5)
		(Tipo ?Tipo)
		)
	)
		(assert (Materias_Posibles
		(NombreMat $?NombreMat)
		(Peso 4.5)
		(Tipo ?Tipo)
		)
	)
)

(defrule Poner_Peso_Materia_Nueva_4
	(Materia 
		(NombreMat $?NombreMat) 
		(Creditos ?Creditos)
		(Flujo No)
		(Proyecto No) 
		(Tipo ?Tipo)
	) 
	(not (exists 
				(Materia_Peso 
				(NombreMat $?NombreMat) 
			)
		)
	)
(test (> ?Creditos 4))
 =>
	(assert (Materia_Peso
		(NombreMat $?NombreMat)
		(Peso 4)
		(Tipo ?Tipo)
		)
	)
		(assert (Materias_Posibles
		(NombreMat $?NombreMat)
		(Peso 4)
		(Tipo ?Tipo)
		)
	)
)
(defrule Poner_Peso_Materia_Nueva_5
	(Materia
		(NombreMat $?NombreMat) 
		(Creditos ?Creditos)
		(Flujo No)
		(Proyecto No) 
		(Tipo ?Tipo)
	) 
	(not (exists 
				(Materia_Peso 
				(NombreMat $?NombreMat) 
			)
		)
	)
(test (<= ?Creditos 4))
 =>
	(assert (Materia_Peso
		(NombreMat $?NombreMat)
		(Peso 3.5)
		(Tipo ?Tipo)
		)
	)
		(assert (Materias_Posibles
		(NombreMat $?NombreMat)
		(Peso 3.5)
		(Tipo ?Tipo)
		)
	)
)
(defrule Poner_Peso_Materia_Nueva_6
			(Materia 
				(NombreMat $?NombreMat) 
				(Creditos ?Creditos)
				(Flujo Si)
				(Proyecto No)
				(Tipo ?Tipo)				
			)
				(not (exists 
						(Materia_Peso 
						(NombreMat $?NombreMat) 
					)
				)
			)
			(test (>= ?Creditos 4))
 =>
	(assert (Materia_Peso
		(NombreMat $?NombreMat)
		(Peso 3.5)
		(Tipo ?Tipo)
		)
	)
		(assert (Materias_Posibles
		(NombreMat $?NombreMat)
		(Peso 3.5)
		(Tipo ?Tipo)
		)
	)
)
(defrule Poner_Peso_Materia_Nueva_7
			(Materia 
				(NombreMat $?NombreMat) 
				(Creditos ?Creditos)
				(Flujo No)
				(Proyecto Si) 
				(Tipo ?Tipo)
			)
			(not (exists 
					(Materia_Peso 
					(NombreMat $?NombreMat) 
				)
			)
		)
			(test (<= ?Creditos 4))
 =>
	(assert (Materia_Peso
		(NombreMat $?NombreMat)
		(Peso 4.0)
		(Tipo ?Tipo)
		)
	)
		(assert (Materias_Posibles
		(NombreMat $?NombreMat)
		(Peso 4.0)
		(Tipo ?Tipo)
		)
	)
)
(defrule Poner_Peso_Materia_Nueva_8
			(Materia 
				(NombreMat $?NombreMat) 
				(Creditos ?Creditos)
				(Flujo No)
				(Proyecto Si) 
				(Tipo ?Tipo)
			)
			(not (exists 
				(Materia_Peso 
				(NombreMat $?NombreMat) 
					)
				)
			)
			(test (> ?Creditos 4))
 =>
	(assert (Materia_Peso
		(NombreMat $?NombreMat)
		(Peso 4.5)
		(Tipo ?Tipo)
		)
	)
		(assert (Materias_Posibles
		(NombreMat $?NombreMat)
		(Peso 4.5)
		(Tipo ?Tipo)
		)
	)
)