import { NestFactory } from '@nestjs/core';
import { AppModule } from './app.module';
import { Logger, ValidationPipe } from '@nestjs/common';
import { TransformInterceptor } from './common/interceptors/transform.interceptor';
import { DocumentBuilder, SwaggerModule } from '@nestjs/swagger';

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

  // Configuración de Swagger
  const config = new DocumentBuilder()
    .setTitle('User Management API')
    .setDescription(
      'API RESTful para la gestión de usuarios con autenticación y encriptación de contraseñas',
    )
    .setVersion('1.0.0')
    .addTag('Users', 'Operaciones CRUD para gestión de usuarios')
    .addTag('Health', 'Endpoints de verificación de salud del servicio')
    .addServer('http://localhost:3005', 'Servidor de Desarrollo')
    .setContact(
      'Soporte Técnico',
      'https://github.com/maximovj/crud-microservicio-multiplataforma',
      '',
    )
    .setLicense('MIT', 'https://opensource.org/licenses/MIT')
    .build();

  const documentFactory = () => SwaggerModule.createDocument(app, config);
  SwaggerModule.setup('api-docs', app, documentFactory, {
    customSiteTitle: 'User Management API - Swagger',
    customCss: `
      .swagger-ui .topbar { background-color: #2c3e50; }
      .swagger-ui .info .title { color: #2c3e50; }
    `,
    swaggerOptions: {
      persistAuthorization: true,
      displayRequestDuration: true,
      filter: true,
      showExtensions: true,
      showCommonExtensions: true,
      tryItOutEnabled: true,
    },
  });

  await app.listen(port); // Se modifica el puerto
  logger.log(`App running on port: ${port}`);
}
bootstrap();
