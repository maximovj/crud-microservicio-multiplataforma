import {
  ConflictException,
  Injectable,
  InternalServerErrorException,
  Logger,
  NotFoundException,
} from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { QueryFailedError, Repository } from 'typeorm';
import { CreateUserDto } from './dto/create-user.dto';
import { UpdateUserDto } from './dto/update-user.dto';
import { User } from './entities/user.entity';

@Injectable()
export class UsersService {
  private readonly logger = new Logger(UsersService.name);

  constructor(
    @InjectRepository(User)
    private usersRepository: Repository<User>,
  ) {}

  async create(createUserDto: CreateUserDto) {
    try {
      const user = this.usersRepository.create(createUserDto);
      return await this.usersRepository.save(user);
    } catch (e: any) {
      this.handleCatchError(e, `Hubo un error al crear un nuevo usuario`);
    }
  }

  async findAll() {
    try {
      return await this.usersRepository.find();
    } catch (e: any) {
      this.handleCatchError(e, `Hubo un error al mostrar todos los usuarios`);
    }
  }

  async findOne(id: number) {
    try {
      const user = await this.usersRepository.findOne({
        where: { id: id },
      });
      if (!user) {
        throw new NotFoundException(`Usuario con ID (${id}) no encontrado`);
      }
      return user;
    } catch (e) {
      this.handleCatchError(
        e,
        `Hubo un error al mostrar usuario con ID (${id})`,
      );
    }
  }

  async update(id: number, updateUserDto: UpdateUserDto) {
    try {
      // Buscar el usuario primero
      const user = await this.findOne(id);
      if (user) {
        // Actualizar con los nuevos datos
        Object.assign(user, updateUserDto);
        return await this.usersRepository.save(user);
      }
    } catch (e: any) {
      this.handleCatchError(
        e,
        `Hubo un error al actualizar usuario con ID (${id})`,
      );
    }
  }

  async remove(id: number) {
    try {
      const result = await this.usersRepository.delete(id);
      if (result.affected === 0) {
        throw new NotFoundException(`Usuario con ID (${id}) no encontrado`);
      }
      return { message: `Usuario con ID (${id}) eliminado correctamente` };
    } catch (e: any) {
      this.handleCatchError(
        e,
        `Hubo un error al eliminar usuario con ID (${id})`,
      );
    }
  }

  // 🛠️ Métodos auxiliares
  private getDatabaseErrorCode(error: QueryFailedError): string | undefined {
    // eslint-disable-next-line @typescript-eslint/no-unsafe-return, @typescript-eslint/no-unsafe-member-access
    return (error.driverError as any)?.code;
  }

  private isDuplicateError(code?: string): boolean {
    const duplicateCodes = [
      'ER_DUP_ENTRY', // MySQL
      '1062', // MySQL (código numérico)
    ];
    return code ? duplicateCodes.includes(code) : false;
  }

  private handleCatchError(e: any, message: string | null = null) {
    this.logger.error(message || 'Hubo un error en el proceso');

    if (e instanceof QueryFailedError) {
      // eslint-disable-next-line @typescript-eslint/no-unsafe-argument
      const error = this.getDatabaseErrorCode(e);
      if (this.isDuplicateError(error)) {
        throw new ConflictException('El email ya esta registrado');
      }
    }

    if (e instanceof NotFoundException) {
      throw new NotFoundException(e?.message);
    }

    throw new InternalServerErrorException(
      "Hubo un error en el servicio/base de datos de 'users'",
    );
  }
}
