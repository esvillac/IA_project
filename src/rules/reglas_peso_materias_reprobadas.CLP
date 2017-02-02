;Reglas para determinar el peso de las materias reprobadas por el estudiante
(defrule Peso_Numero_Materia_Reprobada_0
    (Numero_Repro
             (NombreEst $?NombreEst)     
             (Numero ?Numero)
    ) 
    (test (= ?Numero 0))
    =>
        (assert (Peso_Repro
                (Peso   0)
            )
        )

)

(defrule Peso_Numero_Materia_Reprobada_1
    (Numero_Repro
             (NombreEst $?NombreEst)   
             (Numero ?Numero)
    )
    (Veces_Materia_Reprobada
                        (NombreEst $?NombreEst)
                        (MatRepro $?MatRepro)
                        (Veces ?Veces)    
                )
    
     (test (< ?Veces 2))
    (test (= ?Numero 1))
    =>
        (assert (Peso_Repro
                (Peso   3)
            )
        )

)

(defrule Peso_Numero_Materia_Reprobada_2
    (Numero_Repro
             (NombreEst $?NombreEst)   
             (Numero ?Numero)
    )
    (Veces_Materia_Reprobada
            (NombreEst $?NombreEst)
            (MatRepro $?MatRepro)
            (Veces ?Veces)    
    )
    
    (test (< ?Veces 2))
    (test (= ?Numero 2))
    =>
        (assert (Peso_Repro
                (Peso   6)
            )
        )

)

(defrule Peso_Numero_Materia_Reprobada_3
    (Numero_Repro
             (NombreEst $?NombreEst)
             (Numero ?Numero)
    )
    (Veces_Materia_Reprobada
                        (NombreEst $?NombreEst)
                        (MatRepro $?MatRepro)
                        (Veces ?Veces)    
                )
    
    (test (< ?Veces 2))
    (test (>= ?Numero 3))
    =>
        (assert (Peso_Repro
                (Peso   10)
            )
        )

)

(defrule Materia_A_Prueba
    (Veces_Materia_Reprobada
        (NombreEst $?NombreEst)
        (MatRepro $?MatRepro)
        (Veces ?Veces)
                  
    ) 
    (test (>= ?Veces 2))
    =>
        (assert (Peso_Repro
                (Peso   20)
            )
        )

)
