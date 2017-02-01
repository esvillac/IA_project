;Reglas para regular las materias a coger
(defrule Ajuste_Materias_Tomar_Normal
	(Materias_Posibles_Cojer
		(Nombre $?N)
		(Formativas ?Formativas1)
		(Optativas ?Optativas1)
		(Libre_Opcion ?Libre_Opcion)
	) 
	(Respuesta_Numero_Materias 
		(Nombre_Estudiante $?N) 
		(Formativas ?Formativas2)
		(Optativas ?Optativas2)
		(Libre_Opcion ?Libre_Opcion2)
	) 
(test (<= ?Formativas2 ?Formativas1))
(test (<= ?Optativas2 ?Optativas1))
 => 
	(assert (Numero_Formativas
				(Numero ?Formativas2)
			)
	)
	(assert (Numero_Optativas
				(Numero ?Optativas2)
			)
	)
	(assert (Numero_Libre_Opcion
				(Numero ?Libre_Opcion2)
			)
	)
	(assert (Materias_Resultado
				(Formativas  )
				(Optativas  )
				(LibreOpcion  )
			)
	)
)
(defrule Ajuste_Materias_Tomar_D_Formativas
	(Materias_Posibles_Cojer 
		(Nombre $?N) 
		(Formativas ?Formativas1)
		(Optativas ?Optativas1)
		(Libre_Opcion ?Libre_Opcion1)
	) 
	(Respuesta_Numero_Materias 
		(Nombre_Estudiante $?N) 
		(Formativas ?Formativas2)
		(Optativas ?Optativas2)
		(Libre_Opcion ?Libre_Opcion2)
	) 
(test (> ?Formativas2 ?Formativas1))
 => (bind ?TotalOp (+ ?Optativas2 (- ?Formativas2 ?Formativas1))) 
	(assert (Respuesta_Numero_Materias
		(Nombre_Estudiante $?N)
		(Formativas ?Formativas1)
		(Optativas  ?TotalOp)
		(Libre_Opcion ?Libre_Opcion2)
		)
	)
)
(defrule Ajuste_Materias_Tomar_D_Optativas
	(Materias_Posibles_Cojer 
		(Nombre $?N) 
		(Formativas ?Formativas1)
		(Optativas ?Optativas1)
		(Libre_Opcion ?Libre_Opcion1)
	) 
	(Respuesta_Numero_Materias 
		(Nombre_Estudiante $?N) 
		(Formativas ?Formativas2)
		(Optativas ?Optativas2)
		(Libre_Opcion ?Libre_Opcion2)
	) 
(test (<= ?Formativas2 ?Formativas1))
(test (> ?Optativas2 ?Optativas1))
 => (bind ?TotalLb (+ ?Libre_Opcion2 (- ?Optativas2 ?Optativas1))) 
	(assert (Respuesta_Numero_Materias
		(Nombre_Estudiante $?N)
		(Formativas ?Formativas1)
		(Optativas  ?Optativas1)
		(Libre_Opcion ?TotalLb)
		)
	)
)