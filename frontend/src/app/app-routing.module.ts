import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { StudentComponent } from './components/dashboards/student/student.component';
import { TeacherComponent } from './components/dashboards/teacher/teacher.component';
import { LoginComponent } from './components/forms/login/login.component';

const routes: Routes = [
  {path: "login", component: LoginComponent},
  {path: "student-dashboard", component:StudentComponent},
  {path: "teacher-dashboard", component:TeacherComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
