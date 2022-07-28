export class Student {
  username: string = 'Test Username';
  email: string = 'Test Email';
  role: string = 'STUDENT'
  name: string= 'Test Full Name'

  constructor(
    name      : string,
    username  : string,
    email     : string,
    role      : string,
    ){
    this.name = name;
    this.email = email;
    this.role = role;
    this.username = username;
  }
}
