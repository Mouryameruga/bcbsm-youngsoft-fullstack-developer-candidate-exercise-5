import { Component, OnInit } from '@angular/core';
import { AuthService } from '../_services/auth.service';
import { StorageService } from '../_services/storage.service';
import { MailService } from '../_services/mail.service';

const FILE_DOWNLOAD_API = 'http://localhost:8080/api/files/download/';

@Component({
  selector: 'app-inbox',
  templateUrl: './inbox.component.html',
  styleUrls: ['./inbox.component.css']
})
export class InboxComponent implements OnInit {

  

  form: any = {
    username: null,
    password: null
  };
  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';
  mails: any[] = [];

  constructor(private storageService: StorageService, private mailService: MailService) { }

  ngOnInit(): void {
    if (this.storageService.isLoggedIn()) {
      this.isLoggedIn = true;
      this.getUserMails();
    }
  }

  getUserMails(): void {
    this.mailService.getUserMails().subscribe({
      next: data => {
        console.log(data);
        this.mails = data;
      },
      error: err => {
        this.errorMessage = err.error.message;
      }
    });
  }

  downloadFile(fileID: any): void {
    window.open(FILE_DOWNLOAD_API + fileID, '_blank');
    // window.location.href=FILE_DOWNLOAD_API + fileID;
  }
}