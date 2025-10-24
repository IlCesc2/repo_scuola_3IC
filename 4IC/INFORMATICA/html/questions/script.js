const MULTIPLE="multiple"
const TRUEORFALSE = "boolean"
const NOFQUESTIONS= 15
let questions = []
let currentQuestion = 1

const fetchCategories = async ()=>{
    const response = fetch("https://opentdb.com/api_category.php")
    const values = await response.then(val => val.json())
    console.log(values.trivia_categories)
    return values
}
const initCategories = async ()=>{
    fetchCategories().then((categories)=>{
        const element = document.getElementById("categories")
     
        for(const key in categories.trivia_categories){
            const newElement = document.createElement("option")
            const category = categories.trivia_categories[key]
            console.log(category)
            newElement.value= category.id
            newElement.text=category.name
            element.appendChild(newElement)
        }
     
    })
  
}

const fetchQuestions=async (difficulty, category, type)=>{
    const response = fetch(`https://opentdb.com/api.php?amount=${NOFQUESTIONS}&category=${category}&difficulty=${difficulty}&type=${type}`)
    const values = await response.then(val => val.json())
    console.log(values.results)
    return values.results
}

const startGame = async ()=>{
    questions=[]
    const radio = document.querySelector('input[name="rate"]:checked');
    if(radio == null) {
        alert("Select a difficulty!")
        return
    }
    const difficulty= radio.difficulty
    const category = document.getElementById("categories").value
    const rand = Math.floor(Math.random())
    const response = fetchQuestions(difficulty,category, rand == 0 ? MULTIPLE: TRUEORFALSE)
    questions= response
    
}

initCategories()