import { Component, OnInit } from '@angular/core';
import { UserService } from '../_services/user.service';
import { MailService } from '../_services/mail.service';

const FILE_DOWNLOAD_API = 'http://localhost:8080/api/files/download/';

@Component({
  selector: 'app-board-admin',
  templateUrl: './board-admin.component.html',
  styleUrls: ['./board-admin.component.css']
})
export class BoardAdminComponent implements OnInit {
  content?: string;

  constructor(private mailService: MailService) { }

  errorMessage = '';
  mails: any[] = [];

  ngOnInit(): void {
    this.getAllMails();
  }

  getAllMails(): void {
    this.mailService.getAllMails().subscribe({
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
