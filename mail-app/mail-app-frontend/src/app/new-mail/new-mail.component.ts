import { Component, OnInit } from '@angular/core';
import { AuthService } from '../_services/auth.service';
import { StorageService } from '../_services/storage.service';
import { MailService } from '../_services/mail.service';

@Component({
  selector: 'app-new-mail',
  templateUrl: './new-mail.component.html',
  styleUrls: ['./new-mail.component.css']
})
export class NewMailComponent implements OnInit {
  form: any = {
    fromEmailID : null,
    recipientEmailID : null,
    subject : null,
    body : null,
    attachmentID : null,
    file: null
  };
  isRequestSuccess = false;
  isRequestFailed = false;
  isFileUploadSuccess = false;
  errorMessage = '';
  roles: string[] = [];

  constructor(private storageService: StorageService, private mailService: MailService) { }

  ngOnInit(): void {
    if (this.storageService.isLoggedIn()) {
      this.roles = this.storageService.getUser().roles;
      this.form.fromEmailID = this.storageService.getUser().email;
    }
  }

  onSubmit(): void {
    this.mailService.sendMail(this.form).subscribe({
      next: data => {
        console.log(data);
        this.isRequestSuccess = true;
      },
      error: err => {
        this.errorMessage = err.error.message;
        this.isRequestFailed = true;
      }
    });
  }

  uploadFile(event: any): void {
    if (!event.target.files[0]){
      return;
    }
    console.log('uploading file');
    this.mailService.uploadFile(event.target.files[0]).subscribe({
      next: data => {
        this.form.attachmentID = data.message;
        console.log('file upload done');
        console.log(data);
        this.isFileUploadSuccess = true;
        
      },
      error: err => {
        this.errorMessage = err.error.message;
        this.isFileUploadSuccess = false;
        console.error(err.error);
      }
    });
  }

  reloadPage(): void {
    window.location.reload();
  }
}
