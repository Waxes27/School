import { School } from "./school.model";

export class Teacher {
  username: string = 'Test Username';
  email: string = 'Test Email';
  userRole: string = 'TEACHER'
  name: string= 'Test Full Name'
  school: School | undefined;

  constructor(
    name      : string,
    username  : string,
    email     : string,
    school    : School,
    userRole : string

    ){
    this.name = name;
    this.email = email;
    this.username = username;
    this.school = school
    

    console.log(this.school);

  }
}
