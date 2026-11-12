import * as obj from "./saludo.mjs"

console.log(obj.saludar("Martin"));
console.log("Este es el valor de PI: ", obj.PI)
console.log(obj.config.url_api)

import Estudiante from "./Estudiante.js"
import Persona from "./Persona.js";

let estudiante = new Estudiante();
estudiante.nombre = "Martin";
estudiante.carrera = "ISW";

console.log(estudiante.saludar())
console.log(estudiante.estudiar())
console.log(estudiante.verPromedio())

let persona = new Persona("Martin", 26);

console.log(persona.saludar())