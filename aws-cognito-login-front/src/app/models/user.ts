export interface User {
  name:string,
  lastName:string,
  email:string,
  password:string,
  role: Role
}

export interface Role{
  id: number,
  roleName: string
}
