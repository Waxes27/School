import { Teacher } from "./teacher.model";

export class Student {
  username: string = 'Test Username';
  email: string = 'Test Email';
  role: string = 'STUDENT'
  name: string= 'Test Full Name'
  teacher: Teacher | undefined

  constructor(
    name      : string,
    username  : string,
    email     : string,
    role      : string,
    teacher   : Teacher
    ){
    this.name = name;
    this.email = email;
    this.role = role;
    this.username = username;
    this.teacher = teacher
  }
}
