import { Component, OnInit } from '@angular/core';
import { HttpEventType, HttpResponse } from '@angular/common/http';
import { catchError, Observable, of, tap } from 'rxjs';
import { XmlUploadService } from 'src/app/services/xml-upload.service';

type Upload = {
  progress: Observable<number | string | undefined>;
  file: File;
  importId: Observable<number>;
};

@Component({
  selector: 'xml-upload',
  templateUrl: './xml-upload.component.html',
  styleUrls: ['./xml-upload.component.scss'],
})
export class XmlUploadComponent implements OnInit {
  uploadFiles: Upload[] = [];
  uploading = 0;
  textProgress = 'Carregando...';

  constructor(private uploadService: XmlUploadService) {}

  ngOnInit(): void {}

  selectFile(event: any): void {
    if (event.target.files && event.target.files[0]) {
      const files: File[] = event.target.files;
      for (let i = 0; i < files.length; i++) {
        this.uploadFiles.push({
          file: files[i],
          progress: new Observable(),
          importId: new Observable(),
        });
        ++this.uploading;
        this.upload(files[i], this.uploadFiles.length - 1);
      }
    }
  }

  upload(file: File, fileIndex: number): void {
    if (file) {
      this.uploadService.removePrecoMedio(file, (modifiedFile: File) => {
        this.uploadService
          .upload(modifiedFile)
          .pipe(
            tap((event: any) => {
              if (event.type === HttpEventType.UploadProgress) {
                const percentage = Math.round(
                  (100 * event.loaded) / event.total
                );
                this.uploadFiles[fileIndex].progress = of(percentage);
                percentage === 100
                  ? (this.textProgress = 'Processando...')
                  : (this.textProgress = 'Carregando...');
              }
              if (event.type === HttpEventType.Response) {
                --this.uploading;
                this.uploadFiles[fileIndex].importId = of(event.body);
                console.log(event.body);
              }
            }),
            catchError(() => {
              --this.uploading;
              this.uploadFiles[fileIndex].progress = of('Falha');
              return of('Falha');
            })
          )
          .subscribe();
      });
    }
  }
}
