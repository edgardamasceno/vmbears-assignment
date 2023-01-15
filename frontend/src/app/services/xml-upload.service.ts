import { Injectable } from '@angular/core';
import { HttpClient, HttpRequest, HttpEvent } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class XmlUploadService {
  private baseUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) {}

  removePrecoMedio(file: File, callback: any) {
    const reader = new FileReader();
    reader.onload = (e) => {
      const xml = e.target?.result || new ArrayBuffer(0);
      const parser = new DOMParser();
      const doc = parser.parseFromString(xml.toString(), 'application/xml');
      const precoMedio = doc.getElementsByTagName('precoMedio');
      while (precoMedio.length) {
        precoMedio[0].parentNode?.removeChild(precoMedio[0]);
      }
      const modifiedXml = new XMLSerializer().serializeToString(doc);
      const blob = new Blob([modifiedXml], { type: 'text/xml' });
      return callback(new File([blob], file.name, { type: 'text/xml' }));
    };
    reader.readAsText(file);
  }

  upload(file: File): Observable<HttpEvent<any>> {
    const formData: FormData = new FormData();
    formData.append('file', file);

    const req = new HttpRequest('POST', `${this.baseUrl}/upload`, formData, {
      reportProgress: true,
      responseType: 'json',
    });

    return this.http.request(req);
  }

  getRegionsConsolidatedData(): Observable<any> {
    return this.http.get(`${this.baseUrl}/regions`);
  }
}
