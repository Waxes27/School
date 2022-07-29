import { Principal } from "./principal.model";

export class School {
  // {"principal":"{\"name\":\"p p\",\"userRole\":\"PRINCIPAL\",\"email\":\"p\",\"username\":\"p\"}","name":"Prestige College","userRole":"SCHOOL","email":"prestige@gmail.com","username":"prestige"}
  username: string = 'Test Username';
  email: string = 'Test Email';
  userRole: string = 'SCHOOL'
  name: string= 'Test Full Name'
  principal: Principal | undefined

  constructor(
    name      : string,
    username  : string,
    email     : string,
    principal   : Principal
    ){
    this.name = name;
    this.email = email;
    this.username = username;
    this.principal = principal
  }
}

