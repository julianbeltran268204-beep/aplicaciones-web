//  Ejercicio 1: Temporizador con Callback 

function temporizador(callback) {
  setTimeout(() => {
    callback();
  }, 3000);
}

temporizador(() => {
  console.log("Ejercicio 1: ¡Listo! Pasaron 3 segundos.");
});


//  Ejercicio 2: Operaciones Matemáticas 

function calcular(a, b, operacion) {
  const resultado = operacion(a, b);
  console.log(`Ejercicio 2: el resultado es ${resultado}`);
}

function sumar(a, b) {
  return a + b;
}

function restar(a, b) {
  return a - b;
}

function multiplicar(a, b) {
  return a * b;
}

function dividir(a, b) {
  if (b === 0) {
    return "no se puede dividir entre 0";
  }
  return a / b;
}

calcular(10, 5, sumar);       
calcular(10, 5, restar);     
calcular(10, 5, multiplicar); 
calcular(10, 5, dividir);     


//  Ejercicio 3: Preparación de una Receta 

function cortarIngredientes(callback) {
  console.log("Ejercicio 3: cortando ingredientes...");
  setTimeout(() => {
    console.log("Ejercicio 3: ingredientes cortados.");
    callback();
  }, 1000);
}

function cocinar(callback) {
  console.log("Ejercicio 3: cocinando...");
  setTimeout(() => {
    console.log("Ejercicio 3: comida cocinada.");
    callback();
  }, 1500);
}

function servirPlato(callback) {
  console.log("Ejercicio 3: sirviendo el plato...");
  setTimeout(() => {
    console.log("Ejercicio 3: plato servido.");
    callback();
  }, 500);
}

// Callback Hell básico: cada paso empieza cuando termina el anterior
cortarIngredientes(() => {
  cocinar(() => {
    servirPlato(() => {
      console.log("Ejercicio 3: ¡Buen provecho!");
    });
  });
});
