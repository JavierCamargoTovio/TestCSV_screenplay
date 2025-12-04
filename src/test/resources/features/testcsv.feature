Feature: test csv

@testcsv
Scenario: Crear usuarios usando datos del CSV
Given que cargo los usuarios desde el archivo "usuarios.csv"
When envío cada usuario al servicio
Then los usuarios son creados correctamente