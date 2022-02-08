export interface Weather {
  id: string;
  main: string;
  description: string;
  iconUrl: string;
  temp: number;
}

export interface City {
  id: string;
  name?: string;
  code: string;
  weathers: Array<Weather>;
}
