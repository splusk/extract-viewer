import { Component, OnInit } from '@angular/core';
import { File } from './file.model';
import { FileService } from './file.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: [FileService],
})

export class AppComponent implements OnInit {
  errorMessage: string;
  config: any;
  mode = 'Observable';
  
  files: any[] = [{
    'extractType': '',
    'crewGroup': '',
    'base': '',
    'creationDate': '',
    'action': ''
  }];


  constructor(private fileService: FileService) {
    this.getFiles();
  }

  private onRowClick(row: any) {
    console.log(row);
  }

  private getFiles() {
    this.fileService.getFiles().subscribe(
      files => this.files = files,
      error => this.errorMessage = <any>error);
  }

  tableEvents(value: Event): void {
    if (value) {
      console.log(value);
    }
    if (value.type === 'rowClicked') {
      this.fileService.reProcessFile(value);
    }
  }

  ngOnInit(): void {
    return;
  }
}