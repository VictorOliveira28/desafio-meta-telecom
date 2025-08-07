import { Component, Inject, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import {
  FormBuilder,
  FormGroup,
  Validators,
  ReactiveFormsModule,
} from '@angular/forms';
import {
  MatDialogRef,
  MAT_DIALOG_DATA,
  MatDialogModule,
} from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { Product } from '../../core/models/product.model';
import { ProductService } from '../../core/services/product.service';

@Component({
  selector: 'app-product-dialog',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatDialogModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
  ],
  templateUrl: './product-dialog.html',
  styleUrls: ['./product-dialog.scss'],
})
export class ProductDialogComponent implements OnInit {
  productForm: FormGroup;
  isEditMode: boolean;

  constructor(
    private fb: FormBuilder,
    private productService: ProductService,
    public dialogRef: MatDialogRef<ProductDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Product | undefined
  ) {
    this.isEditMode = !!this.data;
    this.productForm = this.fb.group({
      name: ['', Validators.required],
      description: ['', Validators.required],
      price: [null, [Validators.required, Validators.min(0)]],
      quantity: [null, [Validators.required, Validators.min(0)]],
    });
  }

  ngOnInit(): void {
    if (this.isEditMode && this.data) {
      this.productForm.patchValue(this.data);
    }
  }

  onCancel(): void {
    this.dialogRef.close();
  }

  onSave(): void {
    if (this.productForm.invalid) {
      return;
    }

    const formData = this.productForm.value;

    if (this.isEditMode && this.data) {
      this.productService.updateProduct(this.data.id, formData).subscribe({
        next: () => this.dialogRef.close(true),
        error: (err) => console.error('Erro ao atualizar produto', err),
      });
    } else {
      this.productService.createProduct(formData).subscribe({
        next: () => this.dialogRef.close(true),
        error: (err) => console.error('Erro ao criar produto', err),
      });
    }
  }
}
