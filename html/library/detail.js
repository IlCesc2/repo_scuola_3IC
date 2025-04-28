function onPageLoads(){
    const urlParams = new URLSearchParams(window.location.search);
    const id = urlParams.get('id');

}

async function fetchByID(id){
    const url =`https://openlibrary.org/search.json?cover_i=${id}` // "https://openlibrary.org/search.json?q=bible"    
    await fetch(url).then(async (res)=>{
        const data = await res.json()
        results= data.docs
        console.log(results)
        document.getElementById("loading").style.display="none"
    })
}