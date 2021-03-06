import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';
import { File } from './file.model';

@Injectable()
export class FileService {
  private apiFilesUrl = '/files';  // URL to web API
  
  constructor (private http: Http) {}
  
  getFiles(): Observable<File[]> {
    return this.http.get(this.apiFilesUrl)
                    .map(this.extractData)
                    .catch(this.handleError);
  }
  
  reProcessFile(event: any) {
    //make call
    let name = event.data.name;
  }
  
  private extractData(res: Response) {
    let body = res.json();
    return body.data || { };
  }
  
  private handleError (error: Response | any) {
    // In a real world app, you might use a remote logging infrastructure
    let errMsg: string;
    if (error instanceof Response) {
      const body = error.json() || '';
      const err = body.error || JSON.stringify(body);
      errMsg = `${error.status} - ${error.statusText || ''} ${err}`;
    } else {
      errMsg = error.message ? error.message : error.toString();
    }
    console.error(errMsg);
    return Observable.throw(errMsg);
  }
}