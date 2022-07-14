import React from 'react'
import NewComp3 from './NewComp3'

export default function NewComp2(){
    return (
        <div>
            This is also a new comp, but this one is different. And it also calls a third one
            <NewComp3/>
        </div>
    )
}
