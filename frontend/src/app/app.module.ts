import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { XmlUploadComponent } from './components/xml-upload/xml-upload.component';
import { NoopAnimationsModule } from '@angular/platform-browser/animations';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { MatIconModule } from '@angular/material/icon';
import { MatCardModule } from '@angular/material/card';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatListModule } from '@angular/material/list';
import { MatProgressBarModule } from '@angular/material/progress-bar';

const materialModules = [
  MatIconModule,
  MatCardModule,
  MatToolbarModule,
  MatButtonModule,
  MatInputModule,
  MatFormFieldModule,
  MatProgressBarModule,
  MatListModule,
  BrowserAnimationsModule,
];

@NgModule({
  declarations: [AppComponent, XmlUploadComponent],
  imports: [
    BrowserModule,
    HttpClientModule,
    NoopAnimationsModule,
    ...materialModules,
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
