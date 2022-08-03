import { configureStore } from '@reduxjs/toolkit'
import { rnmSlice } from './slices/rickandmorty/'

export const store = configureStore({
  reducer: {
    rnm: rnmSlice.reducer
  },
})