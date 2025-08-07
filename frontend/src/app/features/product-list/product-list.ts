import { Component, OnInit } from '@angular/core';
import { CommonModule, CurrencyPipe } from '@angular/common';
import { MatTableModule } from '@angular/material/table';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import { ProductService } from '../../core/services/product.service';
import { Product } from '../../core/models/product.model';
import { BehaviorSubject, Observable, switchMap } from 'rxjs';
import { ProductDialogComponent } from '../product-dialog/product-dialog';

@Component({
  selector: 'app-product-list',
  standalone: true,
  imports: [
    CommonModule,
    MatTableModule,
    MatButtonModule,
    MatIconModule,
    MatDialogModule,
    CurrencyPipe,
  ],
  templateUrl: './product-list.html',
  styleUrls: ['./product-list.scss'],
})
export class ProductListComponent implements OnInit {
  displayedColumns: string[] = ['id', 'name', 'price', 'quantity', 'actions'];
  products$: Observable<Product[]>;
  private refresh$ = new BehaviorSubject<void>(undefined);

  constructor(
    private productService: ProductService,
    private dialog: MatDialog
  ) {
    this.products$ = this.refresh$.pipe(
      switchMap(() => this.productService.getProducts())
    );
  }

  ngOnInit(): void {}

  openDialog(product?: Product): void {
    const dialogRef = this.dialog.open(ProductDialogComponent, {
      width: '400px',
      data: product, // Envia o produto para o dialog. Se for undefined, é modo de criação.
    });

    dialogRef.afterClosed().subscribe((result) => {
      // Se o dialog retornou 'true', significa que uma operação foi bem-sucedida.
      if (result === true) {
        this.refresh$.next(); // Dispara a atualização da lista
      }
    });
  }

  deleteProduct(id: number, name: string): void {
    if (confirm(`Tem certeza que deseja excluir o produto "${name}"?`)) {
      this.productService.deleteProduct(id).subscribe({
        next: () => {
          console.log('Produto excluído com sucesso');
          this.refresh$.next();
        },
        error: (err) => console.error('Erro ao excluir produto', err),
      });
    }
  }
}
