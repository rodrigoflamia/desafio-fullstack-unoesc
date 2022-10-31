const cep = document.querySelector("#cep")
const mostrarDados = (resultado) => {
	for(const campo in resultado){
		if(document.querySelector("#" + campo)){
			document.querySelector("#" + campo).value = resultado[campo]
		}
	}
}
		
cep.addEventListener("blur", (e) =>{
	let busca = cep.value.replace("-", "")
	const opcoes = {
		method: "GET",
		mode: "cors",
		cache:"default"
	}

	fetch(`https://viacep.com.br/ws/${busca}/json/`, opcoes)
	.then(response => {response.json()
		.then(dados => mostrarDados(dados))
	})
	.catch(e => console.log("Erro ao buscar: " + e, message))
})