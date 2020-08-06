


const rx = require('rxjs')



/*


 appln : modern-nouse


 components/modules/micro-services : 

    -> Door
    -> Light
    -> AC
    ...

*/


const houseStream = new rx.Subject()


//----------------------------------------------------------------
// LIGHT service
//----------------------------------------------------------------

const light = {
    on(event) {
        console.log("light ON " + event.door + " : " + event.floor)
    },
    off(event) {
        console.log("light OFF " + event.door + " : " + event.floor)
    }
}
houseStream.subscribe(event => {
    if (event.type === "open")
        light.on(event)
    if (event.type === "close")
        light.off(event)
})


//----------------------------------------------------------------
// AC service
//----------------------------------------------------------------

const ac = {
    on(event) {
        console.log("AC ON " + event.door + " : " + event.floor)
    },
    off(event) {
        console.log("AC OFF " + event.door + " : " + event.floor)
    }
}

houseStream.subscribe(event => {
    if (event.type === "open")
        ac.on(event)
    if (event.type === "close")
        ac.off(event)
})


//----------------------------------------------------------------
// DOOR service
//----------------------------------------------------------------


class Door {
    open() {
        console.log("door opened")
        let event = { type: 'open', door: 1, floor: 0 }
        houseStream.next(event)
    }
    close() {
        console.log("door closed")
        let event = { type: 'close', door: 1, floor: 0 }
        houseStream.next(event)
    }
}


const door = new Door();

setTimeout(() => {
    door.open()
    setTimeout(() => {
        door.close()
    }, 3000)
}, 3000)