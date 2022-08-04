import { createSlice } from '@reduxjs/toolkit'

export const rnmSlice = createSlice({
  name: 'rnm',
  initialState: {
    isLoading : false, 
    page : 1,
    characters : [],
    character : ""
  },

  reducers: {
    startLoadingCharacters: (state) => {
      state.isLoading = true;
    },

    setCharacters: (state, action) => {
      state.isLoading = false;
      state.page = action.payload.page;
      state.characters = action.payload.characters
    },
    setCharacter: (state, action) => {
      state.isLoading = false;
      state.character = action.payload.character
      
    }
  },
})
  
// Action creators are generated for each case reducer function
export const { startLoadingCharacters, setCharacters, setCharacter } = rnmSlice.actions