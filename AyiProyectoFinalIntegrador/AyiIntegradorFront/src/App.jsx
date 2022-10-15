import { useState } from 'react'
import reactLogo from './assets/react.svg'
import './App.css'
import ColorButtons from './components/Buttons'
import { store } from './store/store'
import { Provider } from 'react-redux'

function App() {
  const [count, setCount] = useState(0)

  return (

    <Provider store={store}>
      <div className="App">
        <ColorButtons/>
      </div>
    </Provider>
  )
}

export default App
