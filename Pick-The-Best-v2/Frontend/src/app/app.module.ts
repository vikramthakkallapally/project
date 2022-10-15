import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './common/header/header.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { SearchComponent } from './search/search/search.component';
import { ElevatedSearchComponent } from './search/elevated-search/elevated-search.component';
import { ContactusComponent } from './request/contactus/contactus.component';
import { RequestComponent } from './request/request/request.component';
import { AboutComponent } from './request/about/about.component';
import { ErrorComponent } from './request/error/error.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { AddItemsComponent } from './admin/add-items/add-items.component';
import { ReportComponent } from './admin/report/report.component';
import { ProcessRequestsComponent } from './admin/process-requests/process-requests.component';
import { ViewItemsComponent } from './admin/view-items/view-items.component';
import { ViewFeebacksComponent } from './admin/view-feebacks/view-feebacks.component';
import { LoginComponent } from './common/login/login.component';
import { AuthGuard } from './_auth/auth.guard';
import { AuthInterceptor } from './_auth/auth.intercepter';
import { RegisterComponent } from './common/register/register.component';
import { UserService } from './service/user-service.service';
import { ResetPasswordComponent } from './common/reset-password/reset-password.component';
import { ChatComponent } from './common/chat/chat.component';


@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    SearchComponent,
    ElevatedSearchComponent,
    ContactusComponent,
    RequestComponent,
    AboutComponent,
    ErrorComponent,
    AddItemsComponent,
    ReportComponent,
    ProcessRequestsComponent,
    ViewItemsComponent,
    ViewFeebacksComponent,
    LoginComponent,
    RegisterComponent,
    ResetPasswordComponent,
    ChatComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgbModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [
    AuthGuard,{
      provide : HTTP_INTERCEPTORS,
      useClass:AuthInterceptor,
      multi:true
    },
    UserService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
