import { createSlice } from '@reduxjs/toolkit'

export const rnmSlice = createSlice({
  name: 'rnm',
  initialState: {
    page : 1,
    characters : [],
    isLoading : false, 
  },

  reducers: {
    startLoadingCharacters: (state) => {
      state.isLoading = true;
    },

    setCharacters: (state, action) => {
      console.log(action);
      state.isLoading = false;
      state.page = action.payload.page;
      state.characters = action.payload.characters
    }
  },
})

// Action creators are generated for each case reducer function
export const { startLoadingCharacters, setCharacters } = rnmSlice.actions