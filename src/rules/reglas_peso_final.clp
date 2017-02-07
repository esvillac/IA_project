; Reglas para poner peso final a las materias
(defrule Poner_Peso_Final
	(Numero_Estudiantes 
		(Numero ?NumeroEstudiantes)
	) 
	(Materias_Posibles 
		(NombreMat $?NombreMat) 
		(Peso ?Peso)
		(Tipo ?Tipo)
	) 
	(Materia_Repro 
		(NombreMat $?NombreMat) 
		(Numero ?Numero)
	) 
 => (bind ?Total (* (/ (- ?NumeroEstudiantes ?Numero) ?NumeroEstudiantes) (- 10 ?Peso))) 
	(assert (Materia_Final_Peso
		(NombreMat $?NombreMat)
		(Peso ?Total)
		(Tipo ?Tipo)
		)
	)
)
(defrule Poner_Peso_Final_No_Repro
	(Materias_Posibles 
		(NombreMat $?NombreMat) 
		(Peso ?Peso)
		(Tipo ?Tipo)
	) 
	(not (exists
			(Materia_Repro 
				(NombreMat $?NombreMat) 
			)
		)
	)
 => (bind ?Total (- 10 ?Peso))
	(assert (Materia_Final_Peso
		(NombreMat $?NombreMat)
		(Peso ?Total)
		(Tipo ?Tipo)
		)
	)
)
