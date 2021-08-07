# Backend De Encuesta de estilos musicales

_Este proyecto corresponde al backend de la encuesta de preferencias musicales de los usuarios. Fue hecho en Java con el framework Spring _

## Comenzando 🚀

_Estas instrucciones te permitirán obtener una copia del proyecto en funcionamiento en tu máquina local para propósitos de desarrollo y pruebas._

Mira **Deployment** para conocer como desplegar el proyecto.

### Pre-requisitos 📋

_Que cosas necesitas para instalar el software y como instalarlas?_

Necesitas tener preferentemente el IDE Spring Tool Suite o cualquier IDE compatible con JAVA si es Eclipse bajar plugin para spring, contar con OpenJDK  o JDK.
Este proyecto fue hecho con OpenJDK 14. Tener cualquier tipo de base de datos en el computador ( En este proyecto se trabajo con MySQL). Tener instalado  en el IDE
la libreria Lombok. Lo anterior no es obligatoio ya que es para evitar hacer manual los getter y setter y el constructor de las clases. Se debe contar con Postman
o cualquier software para probar las rutas de la API. Ver en elproyecto la siguiente ruta de carpeta (se llama encuessta) : src/main/resources/application.properties en este ultimo archivo modificar los parametros de conexion a la base de datos segun sea la preferencia.

### Instalación 🔧

1. Una vez que se descarga el proyecto se debe ejecutar en el IDE 
2. En postman se debe seguir los siguientes pasos:
       . Señalar que se hara un Post
       . En body poner como key : 
                                a) username y en value admin (usuario administrador)
                                b) password y en value 12345
                                c) grand_type  y en value password
                                
        . La dirección del endpoint para hacer login y obtener el token es : localhost:8080/oauth/token
        . Copiar el token que sale en el resultado (sale indicado en los resultados que da postman)
        . localhost:8080/api/gustos y localhost:8080/api/gustos/page/0 no se necesita autentificarse 
        . si una ruta pide autorizacion agregar en la opcion Authorization poner type Bearer Token y en token pegar el token que se obtuvo en : localhost:8080/oauth/token
        
 _Ejemplo de obtener datos del API REST_
 
 1 Get a localhost:8080/api/gustos  resultados:
 
     [
    {
        "usuario": {
            "id": 1,
            "username": "patricio",
            "password": "$2a$10$Pg75KKTEtfrkAG8QczRa5e198RwKzWlG9uQaGY4LHPDKPr.2X8IAK",
            "enabled": true,
            "nombre": "Patricio",
            "apellido": "Contreras",
            "roles": [
                {
                    "id": 1,
                    "nombre": "ROLE_USER"
                }
            ]
        },
        "tipoMusica": {
            "id": 2,
            "nombre": "Pop"
        },
        "email": "patorma@yahoo.com"
    },
    {
        "usuario": {
            "id": 1,
            "username": "patricio",
            "password": "$2a$10$Pg75KKTEtfrkAG8QczRa5e198RwKzWlG9uQaGY4LHPDKPr.2X8IAK",
            "enabled": true,
            "nombre": "Patricio",
            "apellido": "Contreras",
            "roles": [
                {
                    "id": 1,
                    "nombre": "ROLE_USER"
                }
            ]
        },
        "tipoMusica": {
            "id": 4,
            "nombre": "Visual Kei"
        },
        "email": "mevoya@hotmail.com"
    }
]

2. Post (para autentificarse) localhost:8080/oauth/token resultado: 

{
    "access_token": "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJpbmZvX2FkaWNpb25hbCI6IkhvbGEgcXVlIHRhbCE6ICBhZG1pbiIsInVzZXJfbmFtZSI6ImFkbWluIiwic2NvcGUiOlsicmVhZCIsIndyaXRlIl0sImFwZWxsaWRvIjoiRG9lIiwiZXhwIjoxNjI4Mzc5MDkxLCJub21icmUiOiJKb2huIiwiYXV0aG9yaXRpZXMiOlsiUk9MRV9BRE1JTiIsIlJPTEVfVVNFUiJdLCJqdGkiOiIyY2ZmYTliNi02ZWRiLTQ4Y2UtOWQ2Zi0yOWRhZGRlYTNlZjIiLCJjbGllbnRfaWQiOiJhbmd1bGFyYXBwIn0.eGi8BdBy0SbrqoHABgtioErc-ca38aGX0SfLDdeAU3ssnPR41GirnCoetKjmr34Um8xjDVxuC9QW9_6N6pWb20BB2Q62OK0j1TsDEyL3TVXuLh2Zn2aIGQz1UQEOGJZIBFH0TeJEK_FHTfqwy1XTTynH4LNFgimdXTPueDK3rLWlctoVEA6-Jd5TAmFa4rv6dQgjpgzdhFiT0r_XVRU_gpFESvGcJAucSY_e8QJHLTqPk5iJtpo0_GLq4O_-1dzkeNGIqhSSwEMg94YCHty-h-X2BIVU9iC5E5MgOo-EG5s8qmaiX7CzPwC39-mfcyuH5-4CaIdHXPJWebPc-Nf1Eg",
    "token_type": "bearer",
    "refresh_token": "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJpbmZvX2FkaWNpb25hbCI6IkhvbGEgcXVlIHRhbCE6ICBhZG1pbiIsInVzZXJfbmFtZSI6ImFkbWluIiwic2NvcGUiOlsicmVhZCIsIndyaXRlIl0sImFwZWxsaWRvIjoiRG9lIiwiYXRpIjoiMmNmZmE5YjYtNmVkYi00OGNlLTlkNmYtMjlkYWRkZWEzZWYyIiwiZXhwIjoxNjI4Mzc5MDkxLCJub21icmUiOiJKb2huIiwiYXV0aG9yaXRpZXMiOlsiUk9MRV9BRE1JTiIsIlJPTEVfVVNFUiJdLCJqdGkiOiIxOThlMmFkNy0wMjVmLTQxOWItOTQyZi0yMzI0MmUzOTcxNGEiLCJjbGllbnRfaWQiOiJhbmd1bGFyYXBwIn0.mm-h-WesEcMyz7MDzcSbes7EzJnxqZLklDNkh1UwVonvHDBYFxGkwzd2BIrl_P1yjAarRvZdwBP5qkN6EcaW9cgCLbfl2eRYxH9czF5I4xFX5NblZ6cGzav-sxs-KweIw0sb-7iCmYi2eEgRrN3jI41T5ZARuEYBefxxRQU0Sr0T_ex-4NwIBQgxPUl3vNoKurRJ4aMS6cLOx2NuTzLaoGFpYjdQLtN7DwAGkG4MFEqOxvZ1EiJQOXp7_qWR5yeCMjNu_DRdmFZLaPADfHRV_DiAG_V17ghpJ9j1IrWNTidq26Hh3FrunKUluX-CNFhE4T_XX6Nr_dTzX1FiqmoWfg",
    "expires_in": 3599,
    "scope": "read write",
    "info_adicional": "Hola que tal!:  admin",
    "apellido": "Doe",
    "nombre": "John",
    "jti": "2cffa9b6-6edb-48ce-9d6f-29daddea3ef2"
}
 
 ## Construido con 🛠️
 _Herramientas utilizadas_
 *[spring IDE](https://spring.io/tools) - El framework web usado
 *[Gradle](https://gradle.org/) - Manejador de dependencias

