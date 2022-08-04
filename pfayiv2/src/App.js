import { BrowserRouter, Routes, Route } from 'react-router-dom';
import  {Home} from "../src/components/Home"
import  {Characters} from "../src/components/Characters"
import  Modal from "../src/components/Modal"
import  About from "../src/components/About"
import ResponsiveAppBar  from "./components/visualComps/AppBar"

export const rnm = () => {

  return (
    <>
      <BrowserRouter>
        <ResponsiveAppBar/>
          <Routes>
            <Route path = "/" element = {<Home/>} />
            <Route path = "/about" element = {<About/>} />
            <Route path = "/characters" element = {<Characters/>} />
            <Route path = "/characters/:id" element = {<Modal/>} />
          </Routes>
      </BrowserRouter>
    </>
  )
} 

export default rnm


{/* <h1>Rick and Morty</h1>

      
       */}