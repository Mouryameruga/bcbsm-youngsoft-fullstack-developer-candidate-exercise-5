import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

const MAIL_API = 'http://localhost:8080/api/mail/';
const FILE_API = 'http://localhost:8080/api/files/';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

const fileOptions = {
  //headers: new HttpHeaders({ 'Accept': 'text/plain' })
};

@Injectable({
  providedIn: 'root'
})
export class MailService {
  constructor(private http: HttpClient) {}

  getUserMails(): Observable<any> {
    return this.http.get(
      MAIL_API + 'all-user-mails',
      httpOptions
    );
  }

  getAllMails(): Observable<any> {
    return this.http.get(
      MAIL_API + 'all-mails',
      httpOptions
    );
  }

  sendMail(sendMailRequest: any): Observable<any> {
    return this.http.post(
      MAIL_API + 'send',
      JSON.stringify(sendMailRequest),
      httpOptions
    );
  }

  uploadFile(file: any): Observable<any> {
    const formData = new FormData();
    formData.append("file", file);
    return this.http.post(
      FILE_API + 'upload',
      formData,
      fileOptions
    );
  }

  
}
