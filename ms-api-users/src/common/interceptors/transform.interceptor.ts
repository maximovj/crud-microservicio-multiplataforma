import {
  Injectable,
  NestInterceptor,
  ExecutionContext,
  CallHandler,
} from '@nestjs/common';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { plainToInstance } from 'class-transformer';
import { User } from '../../users/entities/user.entity';

@Injectable()
export class TransformInterceptor implements NestInterceptor {
  intercept(context: ExecutionContext, next: CallHandler): Observable<any> {
    return next.handle().pipe(
      map((data: any) => {
        // Si es null o undefined
        // eslint-disable-next-line @typescript-eslint/no-unsafe-return
        if (!data) return data;
        // Si es un array
        if (Array.isArray(data)) {
          return data.map((item) => plainToInstance(User, item));
        }
        // Si es un objeto
        return plainToInstance(User, data);
      }),
    );
  }
}
