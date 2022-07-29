export class Teacher {
  username: string = 'Test Username';
  email: string = 'Test Email';
  userRole: string = 'TEACHER'
  name: string= 'Test Full Name'

  constructor(
    name      : string,
    username  : string,
    email     : string,

    ){
    this.name = name;
    this.email = email;
    this.username = username;

    console.log(this);

  }
}
