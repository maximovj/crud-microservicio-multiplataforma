import { NestFactory } from '@nestjs/core';
import { AppModule } from './app.module';
const prefix = process.env.PREFIX_MS_API_GATEWAY || 'ms-api-gateway/api';

async function bootstrap() {
  const app = await NestFactory.create(AppModule);
  app.setGlobalPrefix(prefix);
  await app.listen(process.env.PORT ?? 3003); // Se modifica el puerto
}
bootstrap();
