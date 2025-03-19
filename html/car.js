let automobile = {
    marca: "Toyota",
    modello: "Corolla",
    anno: 2022,
};
console.log(automobile)
automobile.colore = "rosso"
automobile.chilometraggio = 50000
console.log(automobile)

automobile.descrizione = function () {
    return `${this.marca} ${this.modello} del ${this.anno}, colore ${this.colore}, con ${this.chilometraggio} km`
}

console.log(automobile.descrizione())

automobile.chilometraggio = 60000
console.log(automobile.chilometraggio);

let nuovaAutomobile = { ...automobile }
console.log(nuovaAutomobile);

nuovaAutomobile.colore = "blu"
console.log(nuovaAutomobile.colore); // Dovrebbe stampare "blu"
console.log(automobile.colore); // Dovrebbe ancora stampare "rosso"


for (entry in automobile) {
    console.log(entry + ", " + automobile[entry])
}
automobile.aggiornaChilometraggio = function (newC = 0) {
    this.chilometraggio = newC
}
automobile.aggiornaChilometraggio(70000);
console.log(automobile.chilometraggio);