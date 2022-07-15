import React, {useState} from 'react';
import ContactData from "./ContactData";
import PersonalData from "./PersonalData";

function NewForm() {
    const [name, setname] = useState()
    const [surname, setsurname] = useState()
    const [mail, setmail] = useState()

  return (
    <form>
        <PersonalData />
        <ContactData />
    </form>
  )
}

export default NewForm

const data={
    name: "",
    surname: "",
    mail: "",
}
