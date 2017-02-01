;Reglas

(defrule Filtro_Numero_Materia_4_R
	(Estudiante 
	(Nombre $?N) 
	(Promedio ?Promedio)
	(NivelEst ?NivelEst)
	(Reprobado Si)
	(Atenuantes Si)
	) 
	(test (<= ?Promedio 8.0))
	(test (>= ?Promedio 7.0))
	 => 
	(assert (Respuesta_Numero_Materias
		(Nombre_Estudiante $?N)
		(Formativas 2)
		(Optativas 1)
		(Libre_Opcion 1)
		(Nivel_Malla ?NivelEst)
		)
	) 
) 

(defrule Filtro_Numero_Materia_4_A
	(Estudiante 
	(Nombre $?N) 
	(Promedio ?Promedio)
	(NivelEst ?NivelEst)
	(Reprobado Si)
	(Atenuantes Si)
	) 
	(test (> ?Promedio 8.0))
	 => 
	(assert (Respuesta_Numero_Materias
		(Nombre_Estudiante $?N)
		(Formativas 3)
		(Optativas 0)
		(Libre_Opcion 1)
		(Nivel_Malla ?NivelEst)
		)
	) 
) 

(defrule Filtro_Numero_Materia_4_B
	(Estudiante 
	(Nombre $?N) 
	(Promedio ?Promedio)
	(NivelEst ?NivelEst)
	(Reprobado Si)
	(Atenuantes Si)
	) 
	(test (< ?Promedio 7.0))
	 => 
	(assert (Respuesta_Numero_Materias
		(Nombre_Estudiante $?N)
		(Formativas 2)
		(Optativas 0)
		(Libre_Opcion 2)
		(Nivel_Malla ?NivelEst)
		)
	) 
) 


(defrule Filtro_Numero_Materia_5_R
	(Estudiante 
	(Nombre $?N) 
	(Promedio ?Promedio)
	(NivelEst ?NivelEst)
	(Reprobado No)
	(Atenuantes Si)
	) 
	(test (<= ?Promedio 8.0))
	(test (> ?Promedio 7.0))
	 => 
	(assert (Respuesta_Numero_Materias
		(Nombre_Estudiante $?N)
		(Formativas 3)
		(Optativas  1)
		(Libre_Opcion 1)
		(Nivel_Malla ?NivelEst)
		)
	)
) 
(defrule Filtro_Numero_Materia_5_A
	(Estudiante 
		(Nombre $?N) 
		(Promedio ?Promedio)
		(NivelEst ?NivelEst)
		(Reprobado No)
		(Atenuantes Si)
	) 
	(test (> ?Promedio 8.0))
	 => 
	(assert (Respuesta_Numero_Materias
		(Nombre_Estudiante $?N)
		(Formativas 4)
		(Optativas  0)
		(Libre_Opcion 1)
		(Nivel_Malla ?NivelEst)
		)
	)
) 
(defrule Filtro_Numero_Materia_5_B
	(Estudiante 
		(Nombre $?N) 
		(Promedio ?Promedio)
		(NivelEst ?NivelEst)
		(Reprobado No)
		(Atenuantes Si)
	) 
	(test (< ?Promedio 7.0))
	 => 
	(assert (Respuesta_Numero_Materias
		(Nombre_Estudiante $?N)
		(Formativas 2)
		(Optativas  0)
		(Libre_Opcion 2)
		(Nivel_Malla ?NivelEst)
		)
	)
) 

(defrule Filtro_Numero_Materia_Promedio_B
	(Estudiante 
		(Nombre $?N) 
		(Promedio ?Promedio)
		(NivelEst ?NivelEst)
		(Reprobado No)
		(Atenuantes No)
	) 
	(test (< ?Promedio 7.0))
 => 
	(assert (Respuesta_Numero_Materias
		(Nombre_Estudiante $?N)
		(Formativas 3)
		(Optativas  0)
		(Libre_Opcion 1)
		(Nivel_Malla ?NivelEst)
		)
	)

)  
(defrule Filtro_Numero_Materia_Promedio_R
	(Estudiante 
		(Nombre $?N) 
		(Promedio ?Promedio)
		(NivelEst ?NivelEst)
		(Reprobado No)
		(Atenuantes No)
	) 
	(test (<= ?Promedio 8.0))
	(test (>= ?Promedio 7.0))
 => 
	(assert (Respuesta_Numero_Materias
		(Nombre_Estudiante $?N)
		(Formativas 3)
		(Optativas  1)
		(Libre_Opcion 1)
		(Nivel_Malla ?NivelEst)
		)
	)

)  
(defrule Filtro_Numero_Materia_Promedio_A
	(Estudiante 
		(Nombre $?N) 
		(Promedio ?Promedio)
		(NivelEst ?NivelEst)
		(Reprobado No)
		(Atenuantes No)
	) 
(test (> ?Promedio 8.0))
 => 
	(assert (Respuesta_Numero_Materias
		(Nombre_Estudiante $?N)
		(Formativas 4)
		(Optativas  1)
		(Libre_Opcion 1)
		(Nivel_Malla ?NivelEst)
		)
	)

) 
(defrule Filtro_Numero_Materia_Reprobado_R 
	(Estudiante 
		(Nombre $?N) 
		(Promedio ?Promedio)
		(NivelEst ?NivelEst)
		(Reprobado Si)
		(Atenuantes No)
	) 
(test (<= ?Promedio 8.0))
(test (>= ?Promedio 7.0))
 => 
	(assert (Respuesta_Numero_Materias
		(Nombre_Estudiante $?N)
		(Formativas 3)
		(Optativas  1)
		(Libre_Opcion 1)
		(Nivel_Malla ?NivelEst)
		)
	)

) 
(defrule Filtro_Numero_Materia_Reprobado_B
	(Estudiante 
		(Nombre $?N) 
		(Promedio ?Promedio)
		(NivelEst ?NivelEst)
		(Reprobado Si)
		(Atenuantes No)
	) 
(test (< ?Promedio 7.0))
 => 
	(assert (Respuesta_Numero_Materias
		(Nombre_Estudiante $?N)
		(Formativas 2)
		(Optativas  0)
		(Libre_Opcion 2)
		(Nivel_Malla ?NivelEst)
		)
	)

) 
(defrule Filtro_Numero_Materia_Reprobado_A
	(Estudiante 
		(Nombre $?N) 
		(Promedio ?Promedio)
		(NivelEst ?NivelEst)
		(Reprobado Si)
		(Atenuantes No)
	) 
(test (> ?Promedio 8.0))
 => 
	(assert (Respuesta_Numero_Materias
		(Nombre_Estudiante $?N)
		(Formativas 4)
		(Optativas  0)
		(Libre_Opcion 1)
		(Nivel_Malla ?NivelEst)
		)
	)

) 