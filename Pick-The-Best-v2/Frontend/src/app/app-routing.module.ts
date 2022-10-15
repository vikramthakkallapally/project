import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddItemsComponent } from './admin/add-items/add-items.component';
import { ProcessRequestsComponent } from './admin/process-requests/process-requests.component';
import { ReportComponent } from './admin/report/report.component';
import { ViewFeebacksComponent } from './admin/view-feebacks/view-feebacks.component';
import { ViewItemsComponent } from './admin/view-items/view-items.component';
import { LoginComponent } from './common/login/login.component';
import { RegisterComponent } from './common/register/register.component';
import { AboutComponent } from './request/about/about.component';
import { ContactusComponent } from './request/contactus/contactus.component';
import { ErrorComponent } from './request/error/error.component';
import { RequestComponent } from './request/request/request.component';
import { ElevatedSearchComponent } from './search/elevated-search/elevated-search.component';
import { SearchComponent } from './search/search/search.component';
import { ResetPasswordComponent } from './common/reset-password/reset-password.component';
import { AuthGuard } from './_auth/auth.guard';
import { ChatComponent } from './common/chat/chat.component';

const routes: Routes = [
  {path: 'user/search',component:SearchComponent,canActivate:[AuthGuard],data:{roles:['ADMIN','USER']}},
  {path: 'user/elevatedSearch',component:ElevatedSearchComponent,canActivate:[AuthGuard],data:{roles:['ADMIN','USER']}},
  {path: 'user/request',component:RequestComponent,canActivate:[AuthGuard],data:{roles:['ADMIN','USER']}},
  {path: 'user/contactus',component:ContactusComponent,canActivate:[AuthGuard],data:{roles:['ADMIN','USER']}},
  {path: 'user/about',component:AboutComponent,canActivate:[AuthGuard],data:{roles:['ADMIN','USER']}},
  {path: 'reset/password',component:ResetPasswordComponent},
  {path: 'admin/addItems',component:AddItemsComponent,canActivate:[AuthGuard],data:{roles:['ADMIN']}},
  {path: 'admin/viewItems',component:ViewItemsComponent,canActivate:[AuthGuard],data:{roles:['ADMIN']}},
  {path: 'admin/viewRequests',component:ProcessRequestsComponent,canActivate:[AuthGuard],data:{roles:['ADMIN']}},
  {path: 'admin/searchReport',component:ReportComponent,canActivate:[AuthGuard],data:{roles:['ADMIN']}},
  {path: 'admin/viewFeedbacks',component:ViewFeebacksComponent,canActivate:[AuthGuard],data:{roles:['ADMIN']}},
  {path: 'login',component:LoginComponent},
  {path: 'register',component:RegisterComponent},
  {path: 'forbidden',component:ErrorComponent},
  {path: 'common/chat',component:ChatComponent,canActivate:[AuthGuard],data:{roles:['ADMIN','USER']}},
  {path: '**',component:SearchComponent,canActivate:[AuthGuard],data:{roles:['ADMIN','USER']}},

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
