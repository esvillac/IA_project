;reglas para respuesta final
(defrule ResultadoF
	(Numero_Formativas 
		(Numero ?NumeroF) 
	)
	(test (> ?NumeroF 0))
	(Materia_Final_Peso 
		(NombreMat $?NombreMat) 
		(Peso ?Peso) 
		(Tipo F) 
	)
	(or
		(and
			(test (< ?Peso 6))
			(test (>= ?Peso 5))		
		)
		(and
			(test (< ?Peso 5))
			(test (>= ?Peso 4))			
		)
		(and
			(test (< ?Peso 4))
			(test (>= ?Peso 3))			
		)
		(and
			(test (< ?Peso 3))	
			(test (>= ?Peso 2))				
		)
		(and
			(test (< ?Peso 2))	
			(test (>= ?Peso 1))				
		)
		(and
			(test (< ?Peso 1))				
		)
	)
	(Materias_Resultado 
		(Formativas $?Formativas) 
	)
	?Materias_Delete <- (Materia_Final_Peso (NombreMat $?NombreMat))
	?Materias_R <- (Numero_Formativas (Numero ?NumeroF))
	?Materias_Result <- (Materias_Resultado (Formativas  $?Formativas))
 =>
	(bind ?TotalF (- ?NumeroF 1))
	(modify ?Materias_R (Numero ?TotalF)) 
	(modify ?Materias_Result 
				(Formativas $?Formativas $?NombreMat)
	)
	(retract ?Materias_Delete) 
	
) 

(defrule ResultadoOP
	(Numero_Optativas 
		(Numero ?NumeroOP) 
	)
	(test (> ?NumeroOP 0))
	(Materia_Final_Peso 
		(NombreMat $?NombreMat) 
		(Peso ?Peso) 
		(Tipo OP) 
	)
	(or
		(and
			(test (< ?Peso 6))
			(test (>= ?Peso 5))		
		)
		(and
			(test (< ?Peso 5))
			(test (>= ?Peso 4))			
		)
		(and
			(test (< ?Peso 4))
			(test (>= ?Peso 3))			
		)
		(and
			(test (< ?Peso 3))	
			(test (>= ?Peso 2))				
		)
		(and
			(test (< ?Peso 2))	
			(test (>= ?Peso 1))				
		)
		(and
			(test (< ?Peso 1))				
		)
	)
	(Materias_Resultado 
		(Optativas $?Optativas) 
	)
	?Materias_Delete <- (Materia_Final_Peso (NombreMat $?NombreMat))
	?Materias_R <- (Numero_Optativas (Numero ?NumeroOP))
	?Materias_Result <- (Materias_Resultado (Optativas  $?Optativas))
 =>
	(bind ?TotalF (- ?NumeroOP 1))
	(modify ?Materias_R (Numero ?TotalF)) 
	(modify ?Materias_Result 
				(Optativas $?Optativas $?NombreMat)
	)
	(retract ?Materias_Delete) 
	
) 

(defrule ResultadoLB
	(Numero_Libre_Opcion 
		(Numero ?NumeroLB) 
	)
	(test (> ?NumeroLB 0))
	(Materia_Final_Peso 
		(NombreMat $?NombreMat) 
		(Peso ?Peso) 
		(Tipo LB) 
	)
	(or
		(and
			(test (< ?Peso 6))
			(test (>= ?Peso 5))		
		)
		(and
			(test (< ?Peso 5))
			(test (>= ?Peso 4))			
		)
		(and
			(test (< ?Peso 4))
			(test (>= ?Peso 3))			
		)
		(and
			(test (< ?Peso 3))	
			(test (>= ?Peso 2))				
		)
		(and
			(test (< ?Peso 2))	
			(test (>= ?Peso 1))				
		)
		(and
			(test (< ?Peso 1))				
		)
	)
	(Materias_Resultado 
		(LibreOpcion $?LibreOpcion) 
	)
	?Materias_Delete <- (Materia_Final_Peso (NombreMat $?NombreMat))
	?Materias_R <- (Numero_Libre_Opcion (Numero ?NumeroLB))
	?Materias_Result <- (Materias_Resultado (LibreOpcion  $?LibreOpcion))
 =>
	(bind ?TotalF (- ?NumeroLB 1))
	(modify ?Materias_R (Numero ?TotalF)) 
	(modify ?Materias_Result 
				(LibreOpcion $?LibreOpcion $?NombreMat)
	)
	(retract ?Materias_Delete) 
	
) 