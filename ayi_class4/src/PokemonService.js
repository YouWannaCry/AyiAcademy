
class PokemonService {
    async getPokemons(){
        const response = await fetch(process.env.REACT_APP_APIBASE)

        return response.json();
    }
    async getPokemonsById(){
        
    }
}

const PokemonService = new PokemonService();

export default PokemonService;