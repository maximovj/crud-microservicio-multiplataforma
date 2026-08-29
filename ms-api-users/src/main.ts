import { NestFactory } from '@nestjs/core';
import { AppModule } from './app.module';
import { Logger, ValidationPipe } from '@nestjs/common';
import { TransformInterceptor } from './common/interceptors/transform.interceptor';

const port = process.env.PORT ?? 3005;
const logLevels: ('verbose' | 'debug' | 'log' | 'warn' | 'error' | 'fatal')[] =
  process.env.LOG_LEVEL === 'debug'
    ? ['error', 'warn', 'log', 'debug', 'verbose']
    : ['error', 'warn', 'log'];

async function bootstrap() {
  const app = await NestFactory.create(AppModule, {
    logger: logLevels,
  });
  app.useGlobalInterceptors(new TransformInterceptor());
  // Sea agrega prefijo api/
  app.setGlobalPrefix('api');
  app.enableCors();
  const logger = new Logger('NestApplication');

  // Habilitar validaciones
  app.useGlobalPipes(
    new ValidationPipe({
      whitelist: true, // despojará al objeto validado (devuelto) de cualquier propiedad que no utilice ningún decorador de validación
      transform: true, // transformar automáticamente las cargas útiles en objetos tipificados según sus clases DTO
      //skipUndefinedProperties: true, // Omitir propiedades no definidas DTO
      //skipNullProperties: true, // Omitir las propiedades null
      skipMissingProperties: true, // Omitit las propiedades nulas y no definidas DTO
      forbidNonWhitelisted: true, // Eliminar propiedes no incluidas en la lista blanca
      transformOptions: {
        enableImplicitConversion: true,
      },
    }),
  );
  await app.listen(port); // Se modifica el puerto
  logger.log(`App running on port: ${port}`);
}
bootstrap();
