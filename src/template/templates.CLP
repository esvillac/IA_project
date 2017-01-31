;Templates
(deftemplate Estudiante
(multislot  Nombre) 
(slot Promedio)
(slot NivelEst)
(slot Reprobado) 
(slot Atenuantes) 
) 
(deftemplate Respuesta_Numero_Materias
	(multislot Nombre_Estudiante) 
	(slot Formativas) 
	(slot Optativas)
	(slot Libre_Opcion)
	(slot Nivel_Malla) 
 ) 
(deftemplate Materias_Posibles_Cojer
	(multislot Nombre)
	(slot Formativas) 
	(slot Optativas)
	(slot Libre_Opcion)
)
(deftemplate Materias_Posibles
	(multislot NombreMat)
	(slot Peso)
	(slot Tipo)
)
(deftemplate Materia
	(multislot NombreMat)
	 (slot Creditos)
	 (slot Dificultad)
	 (slot Flujo)
	 (slot Proyecto)
	 (slot Tipo)
)
(deftemplate Materia_Peso
	(multislot NombreMat)
	 (slot Peso)
	(slot Tipo)
)
(deftemplate Materia_Repro
	(multislot NombreMat)
	 (slot Numero)
)
(deftemplate Materia_Final_Peso
	(multislot NombreMat)
	 (slot Peso)
	 (slot Tipo)
)
(deftemplate Numero_Estudiantes
	(slot Numero)
)
(deftemplate Numero_Formativas
	(slot Numero)
)
(deftemplate Numero_Optativas
	(slot Numero)
)
(deftemplate Numero_Libre_Opcion
	(slot Numero)
)
(deftemplate Materias_Resultado
	(multislot Formativas)
	(multislot Optativas)
	(multislot LibreOpcion)
)
(deffacts Hechos
	(Estudiante
				(Nombre Juan bert)
				(Promedio 8.4)
				(NivelEst 4)
				(Reprobado No)
				(Atenuantes No)
	)
	(Materias_Posibles_Cojer
				(Nombre Juan bert)
				(Formativas 4)
				(Optativas 2)
				(Libre_Opcion 3)
	)

)
